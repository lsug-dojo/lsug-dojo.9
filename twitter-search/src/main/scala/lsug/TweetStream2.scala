package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import twitter4j._
import java.util.Date

import lsug.twitter._

class TweetStream2(val query: Array[String], val userName : String, val password: String)
{
  def this(query: String, userName: String, password: String) =
    this(Array(query), userName, password)
    
  val build: Configuration = 
    TwitterConfig.withUserAndPassword(userName, password)
                                                                 
  //val twitter: Twitter = build.create
  val df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
  
  val twitterStream = 
    build.createStream

  def start() {
    twitterStream.withKeywords(query:_*)
   }

  val errorPrinter = (e: Exception) => { e.printStackTrace() }

  def addListener(listenerFunction: (Tweet) => Unit, errorHandler: (Exception) => Unit = errorPrinter) = { 
    twitterStream.listen(tweet => {
      val time = tweet.getCreatedAt
      val author = tweet.getUser.getScreenName
      val text = tweet.getText
      val friendsCount = tweet.getUser.getFriendsCount
      listenerFunction(Tweet(time, author, text, friendsCount))
    })
    this
  }
}

object TweetStream2 {
  def main(args:Array[String]){
    new TweetStream2(args(0), "lsugoct", "Rfsd5819").addListener(println _).start
  }
}
