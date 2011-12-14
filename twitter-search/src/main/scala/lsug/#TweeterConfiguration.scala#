package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import twitter4j._

package twitter {
  
  case class BoundingBox(southWest: (Double, Double), northEast: (Double, Double)) {
    def toTwitter4JLocation =
      Array(
        Array(southWest._1, southWest._2),
        Array(northEast._1, northEast._2)
      )
  }
  trait Location {
    def boundingBox: BoundingBox
  }
  case class BoundingBoxLocation(override val boundingBox: BoundingBox) extends Location
  case class NamedLocation(name: String) extends Location {
    override def boundingBox = throw new RuntimeException("TODO")
  }
  
  object TwitterConfig {
    def withOAuth(
      twitterConsumerKey: String,
      twitterConsumerSecret: String,
      twitterAccessToken: String,
      twitterAccessTokenSecret: String
    ): Configuration = {
      new ConfigurationBuilder().
      setDebugEnabled(true).
      setOAuthConsumerKey(twitterConsumerKey).
      setOAuthAccessToken(twitterAccessToken).
      setOAuthConsumerSecret(twitterConsumerSecret).
      setOAuthAccessTokenSecret(twitterAccessTokenSecret).
      build()
    }
    def withUserAndPassword(
      twitterUser: String,
      twitterPassword: String
    ): Configuration = { 
      new ConfigurationBuilder().
      setDebugEnabled(true).
      setUser(twitterUser).
      setPassword(twitterPassword).
      build()
    }
  }
  class RichTwitterConfiguration(config: Configuration) {
    def createStream: TwitterStream = new TwitterStreamFactory(config).getInstance()
    def create: Twitter = new TwitterFactory(config).getInstance()
    
  }

  class RichTwitter(twitter: TwitterBase) {
    
  }
  class RichTwitterStream(twitter: TwitterStream) {
    
    def at(location: Location) = {
      val filter = new FilterQuery
      //val londonLocation = Array(51.5, 1.0) // London place_id = 5d838f7a011f4a2d // http://api.twitter.com/1/geo/id/5d838f7a011f4a2d.json
      // http://api.twitter.com/1/geo/id/5d838f7a011f4a2d.json
      // https://dev.twitter.com/docs/api/1/get/geo/search
      // http://jsonlint.com/
      // http://twitter4j.org/en/javadoc/index.html
      // http://twitter4j.org/en/javadoc/twitter4j/Place.html
      filter.locations(location.boundingBox.toTwitter4JLocation)
      twitter.filter(filter)
      twitter
    }
    def withKeywords(terms: String*) = {
      twitter.filter(new FilterQuery().track(terms.toArray))
      twitter
    }
    def listen(f: Status => Unit, errorHandler: Throwable => Unit = _.printStackTrace) = {
      twitter.addListener(new StatusListener {
        override def onStatus(tweet: Status) = {
          f(tweet)
        }
          
        override def onDeletionNotice(p1: StatusDeletionNotice) = {}
  
        def onScrubGeo(p1: Long, p2: Long) = {}
  
        def onTrackLimitationNotice(p1: Int) = {
          println(p1)
        }
  
        def onException(exception: Exception) = {
          errorHandler(exception)
        }
      })
      twitter
    }
    
  }
}

package object twitter {
  implicit def richConfiguration(config: Configuration) = 
    new RichTwitterConfiguration(config)
    
  implicit def richTwitter(twitter: Twitter) =
    new RichTwitter(twitter)
    
  implicit def richTwitterStream(twitter: TwitterStream) =
    new RichTwitterStream(twitter)
}
