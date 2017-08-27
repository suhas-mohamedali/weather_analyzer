name := "weather_analyzer"

version := "1.0"

scalaVersion := "2.12.2"

assemblyJarName in assembly := "weather-analyzer.jar"


libraryDependencies ++= Seq(
"org.scalatest" % "scalatest_2.12" % "3.0.1" % "test",                          
"org.scalactic" % "scalactic_2.12" % "3.0.1"


                              )

unmanagedSourceDirectories in Compile <+= (baseDirectory/"suhas") {_ / "main" / "resources"}
