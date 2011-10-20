package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import twitter4j._

class TwitterStreamListener(val query: String) {
  val cb = new ConfigurationBuilder();
  val build: Configuration = cb.setDebugEnabled(true).setUser("lsugoct").setPassword("Rfsd5819").build()
  val twitterFactory: TwitterFactory = new TwitterFactory(build)
  val twitter: Twitter = twitterFactory.getInstance()
  val df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
  val res = twitter.search(new Query(query))

  val twitterStream = new TwitterStreamFactory(build).getInstance()
  twitterStream.addListener(new StatusListener {
    def onStatus(tweet: Status) {
      val time = df.format(tweet.getCreatedAt)
      val author = tweet.getUser.getScreenName
      val text = tweet.getText
      println("%s@%s %s".format(time, author, text))
    }

    def onDeletionNotice(p1: StatusDeletionNotice) {}

    def onScrubGeo(p1: Long, p2: Long) {}

    def onTrackLimitationNotice(p1: Int) {
      println(p1)
    }

    def onException(p1: Exception) {
      p1.printStackTrace()
    }
  })

  twitterStream.filter(new FilterQuery().track(Array(query)))

//  res.getTweets.foreach ( (tweet: Tweet) => {
//    val time = df.format(tweet.getCreatedAt)
//    val author = tweet.getFromUser
//    val text = tweet.getText
//    println("%s@%s %s".format(time, author, text))
//  })
}

object TwitterStream{
  def main(args:Array[String]){
    new TwitterStreamListener(args(0))
  }
}