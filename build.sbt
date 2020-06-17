lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "tasklist-api",
    version := "1.0",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "mysql" % "mysql-connector-java" % "8.0.20"
    ),
    scalacOptions ++= Seq("-feature", "-deprecation", "-Xfatal-warnings")
  )
