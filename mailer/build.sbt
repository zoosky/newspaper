name := "newspaper-mailer"

version := "1.0"

scalaVersion := "2.11.11"

lazy val akkaVersion = "2.5.2"
lazy val slickVersion = "3.2.0"

val dbDependencies = Seq(
  "org.postgresql" % "postgresql" % "42.1.1",
  "com.typesafe.slick" %% "slick" % slickVersion,
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.14",
  "com.typesafe" % "config" % "1.3.1",
  "ch.lightshed" %% "courier" % "0.1.4",
  "io.scalac" %% "newspaper-schema" % "0.1.0-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test"
) ++ dbDependencies

resolvers += "lightshed-maven" at "http://dl.bintray.com/content/lightshed/maven"

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

flywayUrl := "jdbc:postgresql://192.168.99.100:5432/mailer_db" //TODO: how to provide host?

flywayUser := "mailer" //set in db-scripts, a on top level script
flywayPassword := "mailer123"