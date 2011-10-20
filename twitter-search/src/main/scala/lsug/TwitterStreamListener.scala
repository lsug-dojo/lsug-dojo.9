package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import twitter4j.{Query, Twitter, TwitterFactory}
import scala.collection.JavaConversions._

class TwitterStreamListener {
  val cb = new ConfigurationBuilder();
  val build: Configuration = cb.setDebugEnabled(true).setUser("lsugoct").setPassword("Rfsd5819").build()
  val twitterFactory: TwitterFactory = new TwitterFactory(build)
  val twitter: Twitter = twitterFactory.getInstance()
  val res = twitter.search(new Query("IBM"))
  res.getTweets.foreach (println)
}

object TwitterStream{
  def main(args:Array[String]){
    new TwitterStreamListener()
  }
}