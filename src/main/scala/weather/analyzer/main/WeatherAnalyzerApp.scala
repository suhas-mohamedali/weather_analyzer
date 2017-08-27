package main.scala.weather.analyzer.main

import main.scala.weather.analyzer.processor.WeatherProcessor
import main.scala.weather.analyzer.helper.WeatherUtilHelper

/**
 * WeatherAnalyzer.scala launches the application
 *
 *
 * @author SUHAS A M
 */
object WeatherAnalyzerApp extends App {

  // Validate the provided month and populate month code from arguments
  val providedMonth = WeatherUtilHelper.populateMonthFromInputArgs(args)

  // Populate the location deatils 
  val locationCodeMap = WeatherUtilHelper.fetchLocationDataInMap()

  // Load and analyze the weather data
  val analyzedWeatherData = WeatherProcessor.processWeatherData(locationCodeMap, providedMonth)

  // Print and create the data in output file

  for (value <- analyzedWeatherData)
    println(value)

}