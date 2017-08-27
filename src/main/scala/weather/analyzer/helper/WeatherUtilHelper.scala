package main.scala.weather.analyzer.helper

import java.time._
import java.time.format.DateTimeFormatter
import main.scala.weather.analyzer.common.WeatherConstants

/**
 * The WeatheUtilHelper class methods helps to populate and validate data needed to support the application
 *
 * @author Suhas A M
 */
object WeatherUtilHelper {


  /**
   * Populate and validate the month code entered by the user
   */
  def populateMonthFromInputArgs(args: Array[String]) = {
    
    checkIfTheArgumentsIsEmpty(args)

    val month: String = WeatherUtilHelper.validateEnteredMonthCode(args)

    month

  }
  
  
  def checkIfTheArgumentsIsEmpty(inputParams : Array[String]){
    
    if(inputParams != null && inputParams.isEmpty)        
       throw new IllegalArgumentException(WeatherConstants.MANDATORY_PARAM_ERR_MSG);
        
    
  }

  /**
   * 
   * Validate the input param entered as month code
   *
   */
  def validateEnteredMonthCode(monthCodeAsArgs: Array[String]): String = {

    if (monthCodeAsArgs.size == 0 && monthCodeAsArgs.size > 1)
      throw new IllegalArgumentException(WeatherConstants.MONTH_VALIDATION_ERR_MSG)

    val monthCode = monthCodeAsArgs(0)

    if (monthCode == null)
      throw new IllegalArgumentException(WeatherConstants.MONTH_VALIDATION_ERR_MSG)

    if (monthCode != null && monthCode.trim().length() != 3)
      throw new IllegalArgumentException(WeatherConstants.MONTH_VALIDATION_ERR_MSG)

    val monthCodeInUpperCase = monthCode.toUpperCase();

    monthCodeInUpperCase match {
      case (WeatherConstants.JAN_CODE | WeatherConstants.FEB_CODE | WeatherConstants.MAR_CODE | WeatherConstants.APR_CODE | WeatherConstants.MAY_CODE | WeatherConstants.JUN_CODE | WeatherConstants.JUL_CODE | WeatherConstants.AUG_CODE | WeatherConstants.SEP_CODE | WeatherConstants.OCT_CODE | WeatherConstants.NOV_CODE | WeatherConstants.DEC_CODE) => monthCodeInUpperCase

      case _ => throw new IllegalArgumentException(WeatherConstants.MONTH_VALIDATION_ERR_MSG);
    }

    monthCodeInUpperCase

  }

  /**
   * Populate location details as Map 
   */
  def fetchLocationDataInMap(): Map[String, WeatherUtil.locationCode] = {
    val locationMap = for (
      line <- WeatherUtil.lstCities.map(locationDetails => locationDetails.split(WeatherConstants.PIPE_SEPERATOR_READ))
    ) yield (line(1), WeatherUtil.locationCode(line(0), line(2), line(3)))
    locationMap.toMap
  }

  /**
   * The method current time when the zone id of the location is passed
   */
  def getCurrentTimeAtTheLocation(timeZoneID: String) = {

    val currentTime = (ZonedDateTime.now).withZoneSameInstant(ZoneId.of(timeZoneID)).format(DateTimeFormatter.ofPattern(WeatherConstants.TIME_FORMATTER_PATTERN))

    currentTime

  }

}