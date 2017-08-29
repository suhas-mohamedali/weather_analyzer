# weather_analyzer
Weather analyzer in scala

The weather app provides the estimated weather conditions of various locations for different months.

The locations are assumed to be 

1 NEW YORK
2 LONDON
3 MOSCOW
4 NAIROBI
5 DUBAI
6 MUMBAI
7 HONGKONG
8 SINGAPORE
9 PERTH
10 SYDNEY

Technology  used:

 Language : Scala 2.12.2
 Build tool: SBT 0.13.16
 Testing : scalatest_2.12-3.0.1 and scalactic_2.12-3.0.1

Please execute following commands from the root folder of the project

1. sbt clean
2. sbt update
3. sbt eclipse

Use Scala IDE 4.6.1 OR Eclipse with Scala plugin to import the project

File > Import > General > Existing Project into Workspace

     MAIN OBJECT :  WeatherAnalyzerApp.scala


Run the test cases of the project using following command

   sbt test

Build the project using following command

  sbt assembly
  
COPY the contents of input folder to  /folder_to_source/weather_analyzer/target/scala-2.12/ where jar file is created 

Run the application using following command 

java -jar  /folder_to_source/weather_analyzer/target/scala-2.12/weather-analyzer.jar AUG

this will show the estimated weather conditions of the locations for the month of AUGUST in the following format

LOCATION CODE | LOCATION NAME | COUNTRY NAME | CURRENT TIME | AVERAGE TEMPERATURE | AVERAGE SEA LEVEL PRESSURE | ESTIMATED WEATHER CONDITION

Please change the parameter with other month codes ( for eg: JAN for January, SEP for September etc)to see the changes in the weather conditions