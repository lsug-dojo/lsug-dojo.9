package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import scala.collection.JavaConversions._
import twitter4j.{Tweet, Query, Twitter, TwitterFactory}
import java.text.SimpleDateFormat

class TwitterStreamListener {
  val cb = new ConfigurationBuilder();
  val build: Configuration = cb.setDebugEnabled(true).setUser("lsugoct").setPassword("Rfsd5819").build()
  val twitterFactory: TwitterFactory = new TwitterFactory(build)
  val twitter: Twitter = twitterFactory.getInstance()
  val df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
  val res = twitter.search(new Query("IBM"))
  res.getTweets.foreach ( (tweet: Tweet) => {
    val time = df.format(tweet.getCreatedAt)
    val author = tweet.getFromUser
    val text = tweet.getText
    println("%s@%s %s".format(time, author, text))
  })
}

object TwitterStream{
  def main(args:Array[String]){
    new TwitterStreamListener()
  }
}