import sbt._

class Twitter(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {

  lazy val JavaNet = "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
  val Twitter4J = "Twitter4J" at "http://twitter4j.org/maven2"

  override def libraryDependencies = Set(
    "org.twitter4j" % "twitter4j-core" % "2.2.4",
    "org.twitter4j" % "twitter4j-stream" % "2.2.4",
    "junit" % "junit" % "4.7" % "test",
    "org.scala-tools.testing" %% "specs" % "1.6.8" % "test"
  ) ++ super.libraryDependencies
}
