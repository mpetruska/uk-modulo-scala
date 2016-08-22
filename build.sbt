
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
  version := "1.0.1",
  licenses := Seq("MIT" -> url("http://www.opensource.org/licenses/mit-license.php")),
  crossScalaVersions := Seq("2.10.6", "2.11.7"),
  publishMavenStyle := true,
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
        "org.scalatest" %% "scalatest" % "3.0.0" % Test,
        "org.scalacheck" %% "scalacheck" % "1.13.2" % Test
      )
      ++ (scalaBinaryVersion.value match {
        case "2.11" =>
          Seq(
            "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
          )
        case "2.10" =>
          Seq.empty
      }))
  )
