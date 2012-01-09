name := "lift"

scalaVersion := "2.9.1"

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

resolvers += "Guice" at "http://guice-maven.googlecode.com/svn/trunk"

resolvers += "Selenium" at "http://repo1.maven.org/maven2"

resolvers += "Christoph's Maven Repo" at "http://maven.henkelmann.eu/"

resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

seq(webSettings: _*)

libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.26" % "container"

libraryDependencies ++= {
  val liftVersion = "2.4-M4" // Put the current/latest lift version here
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-openid" % liftVersion % "compile->default")
}

libraryDependencies += "junit" % "junit" % "4.7" % "test"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "0.9.26"

libraryDependencies += "org.specs2" %% "specs2" % "1.6" % "test"

libraryDependencies += "com.h2database" % "h2" % "1.2.147"

libraryDependencies += "com.google.guava" % "guava" % "r09"

//    "org.squeryl" % "squeryl_2.8.1" % "0.9.4-RC3"

libraryDependencies += "com.google.code.guice" % "guice" % "2.0.1"

libraryDependencies += "aopalliance" % "aopalliance" % "1.0"

libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.8.0"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.13"

libraryDependencies += "com.novocode" % "junit-interface" % "0.5" % "test"
