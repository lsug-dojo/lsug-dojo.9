

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import lsug._

class StockMonitorSpec extends FlatSpec 
with ShouldMatchers { 

  "A Stock Monitor" should "parse tweets " in { 

    val sm = StockMonitor

  }

  "A Lsug Tweet" should "handle raw tweet" in { 

    val rawDate = "2011-10-20T10:10:10"
    val rawAuthor = "broski"
    val rawTweet = "my-tweet-is-here" 
    val rawness = rawDate + "@" + rawAuthor + " " + rawTweet
//      "2011-10-20T10:10:10@broski my-tweet-is-here" 

    val lt: LsugTweet = 
      LsugTweetService.unapply(rawness)
    
    lt.time should be (rawDate)
    lt.author should be (rawAuthor)
    lt.body should be (rawTweet)
  }
}

