import Dependencies._
import Settings._

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(Defaults.itSettings)
  .settings(
    name                       := "fs3-playground",
    version                    := "0.1.0",
    scalaVersion               := Versions.scala,
    assembly / assemblyJarName := "fs3-playground.jar",
    assembly / test            := Def.sequential(Test / test, IntegrationTest / test).value,
    scalafmtOnCompile          := false,
    libraryDependencies ++= dependencies ++ testDependencies
  )
  .configs(IntegrationTest)
