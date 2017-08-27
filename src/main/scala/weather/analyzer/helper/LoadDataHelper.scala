package main.scala.weather.analyzer. helper

import scala.io.Source
import java.io.FileNotFoundException
import main.scala.weather.analyzer.common.WeatherConstants

/**
 *
 * LoadDataHelper.scala helps to load the data and filter
 *
 * @author Suhas A M
 *
 */
object LoadDataHelper {

  /**
   *
   * Populated the data from CSV file of location and extract the data according to the input month code
   *
   */
  def getWeatherData(locationFileName: String, strMonthCode: String) = {

    try {
      Source.fromFile(locationFileName.concat(WeatherConstants.CSV_FILE_EXTN)).getLines.filter(_.contains(strMonthCode))
        .map(filtedLinesFromCSV => {
          val extractedData = filtedLinesFromCSV.split(",")
          //If values missing, the assume zero.
          WeatherUtil.inputFileFields(
            if (extractedData(2).trim().length() == 0 || extractedData(2) == "-") 0.0D else extractedData(2).toDouble,
            if (extractedData(3).trim().length() == 0 || extractedData(3) == "-") 0.0D else extractedData(3).toDouble,
            if (extractedData(4).trim().length() == 0 || extractedData(4) == "-") 0.0D else extractedData(4).toDouble,
            if (extractedData(8).trim().length() == 0 || extractedData(8) == "-") 0.0D else extractedData(8).toDouble,
            if (extractedData(9).trim().length() == 0 || extractedData(9) == "-") 0.0D else extractedData(9).toDouble,
            if (extractedData(10).trim().length() == 0 || extractedData(10) == "-") 0.0D else extractedData(10).toDouble,
            if (extractedData(11).trim().length() == 0 || extractedData(11) == "-") 0.0D else extractedData(11).toDouble,
            if (extractedData(12).trim().length() == 0 || extractedData(12) == "-") 0.0D else extractedData(12).toDouble,
            if (extractedData(13).trim().length() == 0 || extractedData(13) == "-") 0.0D else extractedData(13).toDouble,
            if (extractedData(14).trim().length() == 0 || extractedData(14) == "-") 0.0D else extractedData(14).toDouble,
            if (extractedData(15).trim().length() == 0 || extractedData(15) == "-") 0.0D else extractedData(15).toDouble,
            if (extractedData(16).trim().length() == 0 || extractedData(16) == "-") 0.0D else extractedData(16).toDouble,
            if (extractedData(17).trim().length() == 0 || extractedData(17) == "-") 0.0D else extractedData(17).toDouble,
            if (extractedData(18).trim().length() == 0 || extractedData(18) == "-") 0.0D else extractedData(18).toDouble,
            if (extractedData(19).trim().length() == 0 || extractedData(19) == "-") 0.0D else extractedData(19).toDouble)
        }).toList
    } catch {
      case ex: FileNotFoundException => throw new FileNotFoundException(ex.getMessage
        + ". Weather data file loading failed")
    }
  }

}