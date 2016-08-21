
lazy val commonSettings = Seq(
  version := "0.9.0",
  organization := "io.github.mpetruska",
  name := "uk-modulo-scala",
  homepage := Some(url("https://github.com/mpetruska/uk-modulo-scala")),
  scalaVersion := "2.11.7",
  licenses := Seq("MIT" -> url("http://www.opensource.org/licenses/mit-license.php")),
  pomExtra :=
    <scm>
      <url>git@github.com:mpetruska/uk-modulo-scala.git</url>
      <connection>scm:git@github.com:mpetruska/uk-modulo-scala.git</connection>
    </scm>
    <developers>
      <developer>
        <id>mpetruska</id>
        <name>Mark Petruska</name>
      </developer>
    </developers>
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
