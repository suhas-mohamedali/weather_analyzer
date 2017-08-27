package main.scala.weather.analyzer.processor

import main.scala.weather.analyzer.helper.WeatherUtil
import main.scala.weather.analyzer.helper.WeatherUtilHelper
import main.scala.weather.analyzer.helper.LoadDataHelper
import main.scala.weather.analyzer.common.WeatherConstants

/**
 * WeatherProcessor.scala load, fileter the data and analyze the weather condition
 *
 * @author Suhas A M
 */
object WeatherProcessor {

  /**
   * The method extracts the required data from files and populate analyzed weather condition
   */
  def processWeatherData(locationCodeMap: Map[String, WeatherUtil.locationCode], strMonthCode: String) =

    for {
      location <- locationCodeMap.keys.toArray

      val extractedWeatherData = LoadDataHelper.getWeatherData(location, strMonthCode)

      val highTemperatureData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.high_temp).sum) / extractedWeatherData.size).round.toDouble
      val lowTemperatureData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.low_temp).sum) / extractedWeatherData.size).round.toDouble
      val avgTemp = ((highTemperatureData + lowTemperatureData) / 2)

      val highHPAData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.hPa_high).sum) / extractedWeatherData.size).round.toDouble
      val lowHPAData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.hPa_low).sum) / extractedWeatherData.size).round.toDouble
      val avgHPA = (highHPAData + lowHPAData) / 2
      val diffInHPA = highHPAData - lowHPAData

      val highHumidityData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.humidity_perc_high).sum) / extractedWeatherData.size).round.toDouble
      val lowHumidityData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.humidity_perc_low).sum) / extractedWeatherData.size).round.toDouble

      val highWindData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.wind_high).sum) / extractedWeatherData.size).round.toDouble
      val lowWindData = ((extractedWeatherData.map(locationWeatherData => locationWeatherData.wind_avg).sum) / extractedWeatherData.size).round.toDouble

      val analyzedWeatherCondition = WeatherProcessor.analyzeWeatherCondition(highTemperatureData, lowTemperatureData, avgTemp, highHPAData, lowHPAData)

      val currentTimeAtTheLocation = WeatherUtilHelper.getCurrentTimeAtTheLocation(locationCodeMap(location).timeZoneId)

    } yield Array(location,locationCodeMap(location).cityName, locationCodeMap(location).countyName, currentTimeAtTheLocation, "%.2f".format(avgTemp), "%.2f".format(avgHPA), analyzedWeatherCondition).mkString(WeatherConstants.PIPE_SEPERATOR_WRITE)

  /**
   * The method analyze the weather condition on available data
   *
   */
  def analyzeWeatherCondition(highTemperatureData: Double, lowTemperatureData: Double, avgTemp: Double, highHPA: Double, lowHPA: Double): String = {

    var weatherConditionOutCome: String = null

    if (avgTemp <= WeatherConstants.HOT_WEATHER_MAX_LIMIT && avgTemp >= WeatherConstants.HOT_WEATHER_MIN_LIMIT) {

      if (highTemperatureData > WeatherConstants.HOT_WEATHER_MAX_LIMIT && lowTemperatureData > WeatherConstants.HOT_WEATHER_MIN_LIMIT) {

        if (WeatherConstants.HOT_WEATHER_HPA_LIMIT > highHPA && (highHPA - lowHPA) < WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {
          weatherConditionOutCome = WeatherConstants.HOT_WITHOUT_RAIN
        } else {
          weatherConditionOutCome = WeatherConstants.HOT_WITH_RAIN
        }

      } else if (highTemperatureData < WeatherConstants.HOT_WEATHER_MAX_LIMIT && lowTemperatureData < WeatherConstants.HOT_WEATHER_MIN_LIMIT) {

        if (WeatherConstants.HOT_WEATHER_HPA_LIMIT < highHPA && (highHPA - lowHPA) > WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {
          weatherConditionOutCome = WeatherConstants.WARM_WITH_RAIN
        } else {
          weatherConditionOutCome = WeatherConstants.WARM_WITHOUT_RAIN
        }

      } else if (highTemperatureData < WeatherConstants.HOT_WEATHER_MAX_LIMIT && lowTemperatureData > WeatherConstants.HOT_WEATHER_MIN_LIMIT) {

        if ((highHPA - lowHPA) < WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {
          weatherConditionOutCome = WeatherConstants.HOT_WITHOUT_RAIN
        } else {
          weatherConditionOutCome = WeatherConstants.HOT_WITH_RAIN
        }

      }
    } else if (avgTemp < WeatherConstants.HOT_WEATHER_MIN_LIMIT && avgTemp >= WeatherConstants.WARM_WEATHER_MIN_LIMIT) {

      if (WeatherConstants.WARM_WEATHER_HPA_LIMIT < highHPA && (highHPA - lowHPA) >= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {
        weatherConditionOutCome = WeatherConstants.WARM_WITH_RAIN
      } else {
        weatherConditionOutCome = WeatherConstants.WARM_WITHOUT_RAIN
      }

    } else if (avgTemp < WeatherConstants.WARM_WEATHER_MIN_LIMIT && avgTemp >= WeatherConstants.PLEASANT_WEATHER_MIN_LIMIT) {

      if ((highHPA - lowHPA) >= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {
        weatherConditionOutCome = WeatherConstants.PLEASANT_WITH_RAIN
      } else {
        weatherConditionOutCome = WeatherConstants.PLEASANT_WITHOUT_RAIN
      }

    } else if (avgTemp < WeatherConstants.PLEASANT_WEATHER_MIN_LIMIT) {

      if (highTemperatureData >= WeatherConstants.COLD_WEATHER_MAX_LIMIT && (highHPA - lowHPA) >= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {

        weatherConditionOutCome = WeatherConstants.COLD_WITH_RAIN
      } else if (highTemperatureData >= WeatherConstants.COLD_WEATHER_MAX_LIMIT && (highHPA - lowHPA) <= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {
        weatherConditionOutCome = WeatherConstants.COLD_WITHOUT_RAIN
      } else if ((highTemperatureData <= WeatherConstants.COLD_WEATHER_MAX_LIMIT && lowTemperatureData <= WeatherConstants.COLD_WEATHER_MIN_LIMIT) && (highHPA - lowHPA) >= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF) {
        weatherConditionOutCome = WeatherConstants.COLD_WITH_SNOW
      } else if((highTemperatureData <= WeatherConstants.COLD_WEATHER_MAX_LIMIT && lowTemperatureData <= WeatherConstants.COLD_WEATHER_MIN_LIMIT) && (highHPA - lowHPA) >= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF){
        weatherConditionOutCome = WeatherConstants.COLD_WITHOUT_SNOW      
      } else if(highTemperatureData <= WeatherConstants.COLD_WEATHER_MAX_LIMIT && (highHPA - lowHPA) >= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF){
        weatherConditionOutCome = WeatherConstants.COLD_WITH_SNOW
      }else if(highTemperatureData <= WeatherConstants.COLD_WEATHER_MAX_LIMIT && (highHPA - lowHPA) <= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF){
        weatherConditionOutCome = WeatherConstants.COLD_WITHOUT_SNOW
      } else if(highTemperatureData <= WeatherConstants.COLD_WEATHER_MAX_LIMIT && (highHPA - lowHPA) <= WeatherConstants.HOT_WEATHER_HPA_RAIN_DIFF){
        weatherConditionOutCome = WeatherConstants.COLD_WITHOUT_SNOW
      }

    }

    weatherConditionOutCome

  }

}