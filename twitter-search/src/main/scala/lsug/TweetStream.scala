package lsug

import java.text.SimpleDateFormat
import java.util.Date

import twitter4j.conf.Configuration
import twitter4j.conf.ConfigurationBuilder
import twitter4j.FilterQuery
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.TwitterStreamFactory

class TweetStream(val queries: Array[String], val userName: String, val password: String) {

  def this(query: String, userName: String, password: String) = this(Array(query), userName, password)

  val cb = new ConfigurationBuilder();
  val build: Configuration = cb.setDebugEnabled(true).setUser(userName).setPassword(password).build()
  val twitterFactory: TwitterFactory = new TwitterFactory(build)
  val twitter: Twitter = twitterFactory.getInstance()
  val df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
  val twitterStream = new TwitterStreamFactory(build).getInstance()

  def start() {
    twitterStream.filter(new FilterQuery().track(queries))
  }
  
  def query(queries: String*) {
    twitterStream.filter(new FilterQuery().track(Array(queries:_*)))
  }

  val errorPrinter = (e: Exception) => { e.printStackTrace() }

  def addListener(listenerFunction: (Tweet) => Unit, errorHandler: (Exception) => Unit = errorPrinter) = {
    twitterStream.addListener(new StatusListener {
      def onStatus(tweet: Status) {
        val time = tweet.getCreatedAt
        val author = tweet.getUser.getScreenName
        val text = tweet.getText
        val friendsCount = tweet.getUser.getFriendsCount
        listenerFunction(Tweet(time, author, text, friendsCount))
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

case class Tweet(time: Date, author: String, text: String, friendsCount: Int)

object TweetStream {
  def main(args: Array[String]) {
    new TweetStream(args, "lsugoct", "Rfsd5819").addListener(println).start
  }
}
