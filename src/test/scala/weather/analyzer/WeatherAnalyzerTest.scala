package test.scala.weather.analyzer

import org.scalatest.FunSuite
import main.scala.weather.analyzer.helper.WeatherUtilHelper
import main.scala.weather.analyzer.common.WeatherConstants
import main.scala.weather.analyzer.helper.WeatherUtil
import main.scala.weather.analyzer.helper.LoadDataHelper
import main.scala.weather.analyzer.processor.WeatherProcessor

/**
 *
 * WeatherAnalyzerTest test the input parameters, data loading and weather condition analyzing scenarios
 *
 * @author Suhas A M
 *
 *
 *
 */
class WeatherAnalyzerTest extends FunSuite {

  /**
   * Tests error is thrown if no input parameter provided
   */
  test("Test input parameter availability") {
    val thrown = intercept[IllegalArgumentException] {
      WeatherUtilHelper.checkIfTheArgumentsIsEmpty(Array())
    }
    assert(thrown.getMessage == "Please provide month code")
  }

  /**
   * Test whether the method return provided month code after validation
   */
  test("Return month code for valid month code") {

    val monthCode: String = WeatherUtilHelper.validateEnteredMonthCode(Array(WeatherConstants.APR_CODE))
    assert(monthCode.equalsIgnoreCase(WeatherConstants.APR_CODE))

  }

  /**
   * Tests invalid entry of month code
   */
  test("Test valid month code entry") {
    val thrown = intercept[IllegalArgumentException] {
      WeatherUtilHelper.validateEnteredMonthCode(Array("ABC"))
    }
    assert(thrown.getMessage == "Month should be entered as input using 3 alpahabets. For eg: For JANUARY enter JAN")
  }

  /**
   * Tests invalid entry of month code
   */
  test("Test invalid month code entry with less than 3 characters") {
    val thrown = intercept[IllegalArgumentException] {
      WeatherUtilHelper.validateEnteredMonthCode(Array("JA"))
    }
    assert(thrown.getMessage == "Month should be entered as input using 3 alpahabets. For eg: For JANUARY enter JAN")
  }

  /**
   * Tests invalid entry of month code
   */
  test("Test invalid month code entry with more than 3 characters") {
    val thrown = intercept[IllegalArgumentException] {
      WeatherUtilHelper.validateEnteredMonthCode(Array("JANUARY"))
    }
    assert(thrown.getMessage == "Month should be entered as input using 3 alpahabets. For eg: For JANUARY enter JAN")
  }

  /**
   * Test whether populated location map contains valid city key
   */
  test("Test if location map contains valid city keys") {

    val locationMap: Map[String, WeatherUtil.locationCode] = WeatherUtilHelper.fetchLocationDataInMap()
    assert(locationMap.contains("DXB"))
  }

  /**
   *
   * Test the list size should be equal to the number of days of the month passed
   *
   */
  test("Test the extracted data size when city code and month code are given") {

    val extractedWeatherDataList = LoadDataHelper.getWeatherData("JFK", "JUL")

    assert(extractedWeatherDataList.size == 31)
  }

  /**
   * Test hot weather condition
   */
  test("Test weather condition 1") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(41, 35, 37, 999, 996)
    assert(weatherCondition == WeatherConstants.HOT_WITHOUT_RAIN)

  }

  /**
   * Test hot weather condition
   */
  test("Test weather condition 2") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(41, 35, 37, 1020, 996)
    assert(weatherCondition == WeatherConstants.HOT_WITH_RAIN)

  }

  /**
   * Test warm weather condition
   */
  test("Test weather condition 3") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(38, 28, 32, 1005, 1002)
    assert(weatherCondition == WeatherConstants.WARM_WITHOUT_RAIN)

  }

  /**
   * Test warm weather condition
   */
  test("Test weather condition 4") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(38, 28, 32, 1010, 1002)
    assert(weatherCondition == WeatherConstants.WARM_WITH_RAIN)

  }

  /**
   * Test pleasant weather condition
   */
  test("Test weather condition 5") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(25, 10, 18, 1005, 1002)
    assert(weatherCondition == WeatherConstants.PLEASANT_WITHOUT_RAIN)

  }

  /**
   * Test pleasant weather condition
   */
  test("Test weather condition 6") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(25, 10, 18, 1028, 1019)
    assert(weatherCondition == WeatherConstants.PLEASANT_WITH_RAIN)

  }

  /**
   * Test cold weather condition
   */
  test("Test weather condition 7") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(8, 1, 4, 1020, 1019)
    assert(weatherCondition == WeatherConstants.COLD_WITHOUT_RAIN)

  }

  /**
   * Test warm weather condition
   */
  test("Test weather condition 8") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(11, 2, 5, 1028, 1019)
    assert(weatherCondition == WeatherConstants.COLD_WITH_RAIN)

  }

  /**
   * Test warm weather condition
   */
  test("Test weather condition 9") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(4, -4, 1, 1028, 1019)
    assert(weatherCondition == WeatherConstants.COLD_WITH_SNOW)

  }

  /**
   * Test warm weather condition
   */
  test("Test weather condition 10") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(4, -4, 1, 1020, 1019)
    assert(weatherCondition == WeatherConstants.COLD_WITHOUT_SNOW)

  }

  /**
   * Test warm weather condition
   */
  test("Test weather condition 11") {

    val weatherCondition = WeatherProcessor.analyzeWeatherCondition(12, 5, 10, 1020, 1019)
    assert(weatherCondition == WeatherConstants.COLD_WITHOUT_RAIN)

  }

}
  
