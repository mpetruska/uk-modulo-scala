
lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "io.github.mpetruska",
  scalaVersion := "2.11.7",
  pomExtra :=
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
)

lazy val ukModuloScala = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test",
      "org.scalacheck" %% "scalacheck" % "1.13.2" % "test"
    )
  )
