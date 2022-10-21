
lazy val buildSettings = Seq(
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xfatal-warnings"),
  initialize := sys.props.remove("scalac.patmat.analysisBudget")
)

lazy val publishSettings = Seq(
  organization := "com.github.mpetruska",
  name := "uk-modulo-scala",
  homepage := Some(url("https://github.com/mpetruska/uk-modulo-scala")),
  version := "7.30.0",
  licenses := Seq("MIT" -> url("http://www.opensource.org/licenses/mit-license.php")),
  crossScalaVersions := Seq("2.12.13", "2.13.5"),
  publishMavenStyle := true,
  pomExtra :=
    <developers>
      <developer>
        <id>mpetruska</id>
        <name>Mark Petruska</name>
      </developer>
    </developers>,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  Test / publishArtifact := false
)

lazy val ukModuloScala = (project in file("."))
  .settings(buildSettings: _*)
  .settings(publishSettings: _*)
  .settings(
    libraryDependencies ++= (Seq(
        "org.scalatest"  %% "scalatest"  % "3.2.10"  % Test,
        "org.scalacheck" %% "scalacheck" % "1.15.4" % Test,
        "org.scalatestplus" %% "scalacheck-1-15" % "3.2.10.0" % Test,
        "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0"
      ))
  )
