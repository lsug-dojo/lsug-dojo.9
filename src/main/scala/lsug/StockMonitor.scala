
package lsug

case class StockMonitor { 

}

// consider better time type
case class LsugTweet(time: String, author: String, body: String)

object LsugTweetService { 

  def unapply(rawTweet: String) : LsugTweet = { 

    rawTweet match {

      case LsugTweetRE(d,a,b) => 

	LsugTweet(d,a,b)

      case _ =>

	throw new Exception("hi")
    }
  }

  val LsugTweetRE = """([^@]+)@([^ ]+) (.+)""".r
}
