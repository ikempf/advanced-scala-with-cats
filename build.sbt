import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT",
      scalacOptions += "-Ypartial-unification"
    )),
    name := "advanced-scala-with-cats",
    libraryDependencies ++= List(scalaTest % Test, cats)
  )
