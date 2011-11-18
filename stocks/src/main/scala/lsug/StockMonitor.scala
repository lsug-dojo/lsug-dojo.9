
package lsug

object StockMonitor extends App { 

  for (ln <- io.Source.stdin.getLines) { 

    val lt = LsugTweetService.unapply(ln)
    printf("author: %s - date: %s\ntweet: [%s]\n", lt.author, lt.time, lt.body)
  }
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
