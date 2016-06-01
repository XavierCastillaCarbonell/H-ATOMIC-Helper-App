name := "H-ATOMIC_HelperApp"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val specs2Version = "3.7.2"
  val specs2scalaMockVersion = "3.2.2"
  val scalafxVersion = "8.0.92-R10"
  val json4sVersion = "3.3.0"
  val sprayVersion = "1.3.2"
  val michaelpollmeierVersion = "3.1.0-incubating"
  val apacheTinkerpopVersion = "3.1.0-incubating"
  val neo4jTinkerPopApiVersion = "0.3-2.3.0"

  Seq(
    // Specs 2
    "org.specs2" %% "specs2-core" % specs2Version % "test",
    "org.specs2" %% "specs2-matcher-extra" % specs2Version % "test",
    "org.specs2" %% "specs2-junit" % specs2Version % "test",
    "org.scalamock" %% "scalamock-specs2-support" % specs2scalaMockVersion % "test",
    //Scalafx
    "org.scalafx" %% "scalafx" % scalafxVersion,
    //JSON Support
    "org.json4s" %% "json4s-jackson" % json4sVersion,
    "org.json4s" %% "json4s-ext" % json4sVersion,
    "io.spray" %% "spray-json" % sprayVersion,
    //gremlin
    "com.michaelpollmeier" %% "gremlin-scala" % michaelpollmeierVersion,
    "org.apache.tinkerpop" % "neo4j-gremlin" % apacheTinkerpopVersion,
    "org.neo4j" % "neo4j-tinkerpop-api-impl" % neo4jTinkerPopApiVersion,
    "org.slf4j" % "slf4j-simple" % "1.7.12",
    "org.scalatest" %% "scalatest" % "2.2.5" % "test"
  )
}
