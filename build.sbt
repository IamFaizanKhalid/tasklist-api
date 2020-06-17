lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "tasklist-api",
    version := "1.0",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
    ),
    scalacOptions ++= Seq("-feature", "-deprecation", "-Xfatal-warnings")
  )
