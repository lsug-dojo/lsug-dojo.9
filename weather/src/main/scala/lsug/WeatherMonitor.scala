package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import twitter4j._

object WeatherMonitor extends App { 
  new TwitterStreamListenerBase {
      override lazy val twitterUser: String = "coreideas"
      override lazy val twitterPassword: String = "wsxzaq"
    
    override def configureStream(twitterStream: TwitterStream): Unit = {
      //val res = twitter.search(new Query(query))
      val filter = new FilterQuery
      //val londonLocation = Array(51.5, 1.0) // London place_id = 5d838f7a011f4a2d // http://api.twitter.com/1/geo/id/5d838f7a011f4a2d.json
      // http://api.twitter.com/1/geo/id/5d838f7a011f4a2d.json
      // https://dev.twitter.com/docs/api/1/get/geo/search
      // http://jsonlint.com/
      val londonLocation = Array(
        Array(
          -1,
          51.0
        ),
        Array(
          1,
          53.0
        )
      )
      // http://twitter4j.org/en/javadoc/index.html
      // http://twitter4j.org/en/javadoc/twitter4j/Place.html
      filter.locations(londonLocation)
      twitterStream.filter(filter)
      //twitterStream.filter(new FilterQuery().track(Array(args(0))))
    }
    def toRegexWords(as: Array[String]) =
      as.
        map(s => "\\b" + s + "\\b").
        map(_.r)
      
    val goodWeatherTerms = toRegexWords(Array(
      "sunny", "warm", "dry", //"clear", 
      "bright sky"
    ))
    val badWeatherTerms = toRegexWords(Array(
      "cloudy", "rain", "cold", "freezing",
      """(-?[\d+]+(?:.\d+)?)\s*(f(?:arenheit)?|c(?:elcius)?)"""
      //"""\d+c"""
    ))
    //val temperatureCelsiusRx = """temperature (-?[\d+](?:.\d+)?) (F(?:arenheit)?|C(?:elcius)?)""".r
    
    override def tweetReceived(tweet: Status): Unit = {
      val text = tweet.getText.toLowerCase
      val foundGood = goodWeatherTerms.filter(_.findFirstIn(text) != None)
      val foundBad = badWeatherTerms.filter(_.findFirstIn(text) != None)
      
      if (!foundGood.isEmpty || !foundBad.isEmpty) {// || text.contains("temperature")) {
        println(tweet.getText + " @ " + Option(tweet.getPlace).map(_.getName).getOrElse("?"))
      //println(tweet.getText + " @ " + Option(tweet.getPlace).map(_.toString).getOrElse("?"))
      }
    }
  }
}

/*

      callback.foreach(_(tweet))
      Option(tweet.getPlace).foreach(pl => {
        
      })
      //if (print) 
      if (false) {
        val time = df.format(tweet.getCreatedAt)
        val author = tweet.getUser.getScreenName
        val text = tweet.getText
        val place = Option(tweet.getPlace).map(_.getFullName).getOrElse("?")
        println("%s@%s %s | at %s".format(time, author, text, place))
      }
}
*/
