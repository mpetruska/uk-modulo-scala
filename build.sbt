
lazy val buildSettings = Seq(
  scalacOptions ++= Seq("-unchecked", "-deprecation"),
  initialize :=
    (if (scalaBinaryVersion.value == "2.10") sys.props("scalac.patmat.analysisBudget") = "off"
     else sys.props.remove("scalac.patmat.analysisBudget"))
)

lazy val publishSettings = Seq(
  organization := "com.github.mpetruska",
  name := "uk-modulo-scala",
  homepage := Some(url("https://github.com/mpetruska/uk-modulo-scala")),
  version := "5.80.0",
  licenses := Seq("MIT" -> url("http://www.opensource.org/licenses/mit-license.php")),
  crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.4"),
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
  publishArtifact in Test := false
)

lazy val ukModuloScala = (project in file("."))
  .settings(buildSettings: _*)
  .settings(publishSettings: _*)
  .settings(
    libraryDependencies ++= (Seq(
        "org.scalatest" %% "scalatest" % "3.0.1" % Test,
        "org.scalacheck" %% "scalacheck" % "1.13.4" % Test
      )
      ++ (scalaBinaryVersion.value match {
        case "2.11" | "2.12" =>
          Seq(
            "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5"
          )
        case "2.10" =>
          Seq.empty
      }))
  )
