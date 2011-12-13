import sbt._
import Keys._

object TwitterBuild extends Build {

    lazy val twitterSearch = Project(id = "twitter-search",
                           base = file("twitter-search"))

    lazy val stocks = Project(id = "stocks",
                           base = file("stocks")) dependsOn(twitterSearch)

    lazy val friends = Project(id = "friends",
			   base = file("friends")) dependsOn(twitterSearch)

    lazy val emailNotification = Project(id = "email-notification",
                           base = file("email-notification")) dependsOn(twitterSearch)

    lazy val lift = Project(id = "lift",
          base = file("lift")) dependsOn(twitterSearch)

}
