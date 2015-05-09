lazy val root = project.in(file(".")).enablePlugins(PlayJava)

name := """just-play-java"""

version := "1.0-SNAPSHOT"


fork in run := true