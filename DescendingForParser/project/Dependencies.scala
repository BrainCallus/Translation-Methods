import sbt._

object Dependencies {
  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.2.15" % "test"
  lazy val cats = "org.typelevel" %% "cats-core" % "2.10.0"
  lazy val enumeration: Seq[ModuleID] = Seq(
    "com.beachape" %% "enumeratum" % "1.7.3"
  )

}