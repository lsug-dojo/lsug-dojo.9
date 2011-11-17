package lsug

sealed trait Move
case object Buy extends Move
case object Sell extends Move
case object Hold extends Move

object decider {
	
	def signal(twt: LsugTweet) : Move = {
          twt match {
	    case LsugTweet(_,_,"BUY") => Buy
	    case LsugTweet(_,_,"SELL") => Sell 
	    case _ => Hold
	  }
	}
}
