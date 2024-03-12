import Dependencies._
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "Lab4_Generator",
    libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.2.15" % "test"
      , "org.typelevel" %% "cats-core" % "2.10.0"
      , "org.typelevel" %% "cats-effect" % "3.4.8"
      , "commons-io" % "commons-io" % "20030203.000550"
      , "org.antlr" % "antlr4-runtime" % "4.12.0"
      , "org.mockito" % "mockito-all" % "1.10.19"
      , "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.9.5"
      , "com.beachape" %% "enumeratum" % "1.7.3"
    )
  )
