name := "H-ATOMIC_HelperApp"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val scalafxVersion = "8.0.60-R9"
  val json4sVersion = "3.3.0"
  val sprayVersion = "1.3.3"
  Seq(
    //Scalafx
    "org.scalafx" %% "scalafx" % scalafxVersion,
    //JSON Support
    "org.json4s" %% "json4s-jackson" % json4sVersion,
    "org.json4s" %% "json4s-ext" % json4sVersion,
    "io.spray" %% "spray-testkit" % sprayVersion
  )
}
    