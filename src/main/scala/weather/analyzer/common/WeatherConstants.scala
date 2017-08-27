package main.scala.weather.analyzer.common

/**
 * The WeatherConstants.scala provides the constants used for the application
 *
 * @author Suhas A M
 */
object WeatherConstants {

  val JAN_CODE: String = "JAN"
  val FEB_CODE: String = "FEB"
  val MAR_CODE: String = "MAR"
  val APR_CODE: String = "APR"
  val MAY_CODE: String = "MAY"
  val JUN_CODE: String = "JUN"
  val JUL_CODE: String = "JUL"
  val AUG_CODE: String = "AUG"
  val SEP_CODE: String = "SEP"
  val OCT_CODE: String = "OCT"
  val NOV_CODE: String = "NOV"
  val DEC_CODE: String = "DEC"

  val MOSCOW: String = "MOSCOW|DME|RUSSIA|Europe/Moscow"
  val DUBAI: String = "DUBAI|DXB|U A E|Asia/Dubai"
  val PERTH: String = "PERTH|PER|AUSTRALIA|Australia/Perth"
  val NEW_YORK: String = "NEW YORK|JFK|U S A|America/New_York"
  val SYDNEY: String = "SYDNEY|SYD|AUSTRALIA|Australia/Sydney"
  val LONDON: String = "LONDON|LHR|U K|Europe/London"
  val HONG_KONG: String = "HONG KONG|HKG|HONG KONG|Hongkong"
  val MUMBAI: String = "MUMBAI|BOM|INDIA|Asia/Calcutta"
  val SINGAPORE: String = "SINGAPORE|SIN|SINGAPORE|Singapore"
  val NAIROBI: String = "NAIROBI|NBO|KENYA|Africa/Nairobi"

  val HOT_WEATHER_MAX_LIMIT: Double = 40.00
  val HOT_WEATHER_MIN_LIMIT: Double = 30.00
  val HOT_WEATHER_HPA_LIMIT: Double = 1005.00
  val HOT_WEATHER_HPA_RAIN_DIFF: Double = 5.00
  val WARM_WEATHER_HPA_LIMIT: Double = 1010.00
  val WARM_WEATHER_HPA__RAIN_DIFF: Double = 5.00
  val COLD_WEATHER_MAX_LIMIT: Double = 6.00
  val COLD_WEATHER_MIN_LIMIT: Double = 0.00
  val WARM_WEATHER_MIN_LIMIT: Double = 20.00
  val PLEASANT_WEATHER_MIN_LIMIT: Double = 15.00
  val RAIN_CHANCE_HOT: Double = 12.00
  val RAIN_CHANCE_WARM: Double = 9.00
  val RAIN_CHANCE_PLEASANT: Double = 7.00
  val RAIN_CHANCE_COLD: Double = 5.00

  val PIPE_SEPERATOR_READ = "\\|"
  val PIPE_SEPERATOR_WRITE = "|"
  val CSV_FILE_EXTN = ".csv"

  val TIME_FORMATTER_PATTERN = "HH:mm"

  val HOT_WITH_RAIN: String = "Hot climate conditions with chance of rain"
  val HOT_WITHOUT_RAIN: String = "Hot climate conditions with chance no rain"
  val WARM_WITH_RAIN: String = "Warm weather conditions with chance of rain"
  val WARM_WITHOUT_RAIN: String = "Warm weather conditions with chance of no rain"
  val WARM_TO_HOT_WITH_RAIN: String = "Warm to hot weather conditions with chance of no rain"
  val PLEASANT_WITH_RAIN: String = "Pleasant weather conditions with chance of rain"
  val PLEASANT_WITHOUT_RAIN: String = "Pleasant weather conditions with chance of rain"
  val COLD_WITH_RAIN: String = "Cold weather conditions with chance of rain"
  val COLD_WITHOUT_RAIN: String = "Cold weather conditions with chance of no rain"
  val COLD_WITH_SNOW: String = "Cold weather conditions with chance of snow"
  val COLD_WITHOUT_SNOW: String = "Cold weather conditions with no chance of snow"
  val COLD_WITH_SNOW_OR_RAIN: String = "Cold weather conditions with chance of rain or snow"
  val COLD_WEATHER_DEFAULT: String = "Cold weather conditions"

  val MANDATORY_PARAM_ERR_MSG = "Please provide month code"
  val MONTH_VALIDATION_ERR_MSG = "Month should be entered as input using 3 alpahabets. For eg: For JANUARY enter JAN"

}