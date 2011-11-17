package lsug.dojo.twitter.friends

class MostFriends { 

  import lsug._

  import scala.collection.mutable.ListBuffer

  val tweets = ListBuffer[Tweet]()
  
  def startTracking() = { 

   new TweetStream("hi", "username", "password").
    addListener(SetTweetStream _).start
  }

  def SetTweetStream(tweet: Tweet)={
    tweets += tweet
 }
}
