package main.scala.weather.analyzer.helper

import main.scala.weather.analyzer.common.WeatherConstants

/**
 * Util classes for case classes, data fields, location list
 *  
 * @author Suhas A M
 */
object WeatherUtil {

  // Cities with City Airport Code and Country Name
  val lstCities = List(WeatherConstants.LONDON,WeatherConstants.NEW_YORK,  WeatherConstants.NAIROBI, WeatherConstants.MOSCOW, WeatherConstants.DUBAI, WeatherConstants.MUMBAI, WeatherConstants.SINGAPORE, WeatherConstants.HONG_KONG, WeatherConstants.PERTH, WeatherConstants.SYDNEY)

  case class locationCode(cityName: String, countyName: String, timeZoneId: String)

  // Fields in the historical data CSV
  case class inputFileFields(
    high_temp: Double,
    avg_temp: Double,
    low_temp: Double,
    humidity_perc_high: Double,
    humidity_perc_avg: Double,
    humidity_perc_low: Double,
    hPa_high: Double,
    hPa_avg: Double,
    hPa_low: Double,
    visibility_high: Double,
    visibility_avg: Double,
    visibility_low: Double,
    wind_high: Double,
    wind_avg: Double,
    wind_max: Double)

}