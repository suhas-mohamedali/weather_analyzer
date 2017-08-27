# weather_analyzer
Weather analyzer in scala




The weather app provides the estimated weather conditions of various locations for different months.

Technology  used:

 Language : Scala 2.12.2
 Build tool: SBT 0.13.16
 Testing : scalatest_2.12-3.0.1 and scalactic_2.12-3.0.1

Please execute following command from the rrot folder of the project

1. sbt clean
2. sbt update
3. sbt eclipse

Use Scala IDE 4.6.1 OR Eclipse with Scala plugin to import the project

File > Import > General > Existing Project into Workspace

     MAIN OBJECT :  WeatherAnalyzerApp.scala
     
    Select the file and run as scala application ( please provide the input arguments using run RunAs > Run configuration

Run the test cases of the project using following command

   sbt test

Build the project using following command

  sbt assembly

Copy the contents of input folder to the location where jar file "weather-analyzer.jar" is created.

Run the application using following command 

java -jar  /folder_to_source/weather_analyzer/target/scala-2.12/ AUG