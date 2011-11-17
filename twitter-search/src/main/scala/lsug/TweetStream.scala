package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import twitter4j._
import java.util.Date

class TweetStream(val query: String, val userName : String, val password: String) {
  val cb = new ConfigurationBuilder();
  val build: Configuration = cb.setDebugEnabled(true).setUser(userName).setPassword(password).build()
  val twitterFactory: TwitterFactory = new TwitterFactory(build)
  val twitter: Twitter = twitterFactory.getInstance()
  val df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
  val twitterStream = new TwitterStreamFactory(build).getInstance()

  def start() {
    twitterStream.filter(new FilterQuery().track(Array(query)))
   }

  val errorPrinter = (e: Exception) => { e.printStackTrace() }

  def addListener(listenerFunction: (Tweet) => Unit, errorHandler: (Exception) => Unit = errorPrinter) = {
    twitterStream.addListener(new StatusListener {
      def onStatus(tweet: Status) {
        val time = tweet.getCreatedAt
        val author = tweet.getUser.getScreenName
        val text = tweet.getText
        listenerFunction(Tweet(time, author, text))
      }

      def onDeletionNotice(p1: StatusDeletionNotice) {}

      def onScrubGeo(p1: Long, p2: Long) {}

      def onTrackLimitationNotice(p1: Int) {
        println(p1)
      }

      def onException(exception: Exception) {
        errorHandler(exception)
      }
    })
    this
  }
}

case class Tweet(time: Date, author: String, text: String)

object TweetStream {
  def main(args:Array[String]){
    new TweetStream(args(0), "lsugoct", "Rfsd5819").addListener(println _).start
  }
}