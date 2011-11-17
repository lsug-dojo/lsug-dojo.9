name := "lsug.dojo.twitter-search"

version := "1.0"

scalaVersion := "2.9.1"

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

resolvers += "Twitter4J" at "http://twitter4j.org/maven2"

libraryDependencies ++= Seq(
	"org.twitter4j" % "twitter4j-core" % "2.2.5-SNAPSHOT",
	"org.twitter4j" % "twitter4j-stream" % "2.2.5-SNAPSHOT",
	"junit" % "junit" % "4.7" % "test",
	"org.scalatest" %% "scalatest" % "1.6.1" % "test"
)
