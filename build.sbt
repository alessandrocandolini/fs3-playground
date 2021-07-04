import Dependencies._
import Settings._

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    name := "fs3-playground",
    version := "0.1.0",
    scalaVersion := Versions.scala,
    assembly / assemblyJarName := "fs3-playground.jar",
    assembly / test := (Test / test).value,
    scalafmtOnCompile := true,
    libraryDependencies ++= dependencies ++ testDependencies
  )
