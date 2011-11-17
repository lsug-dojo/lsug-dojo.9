package lsug.dojo.twitter.friends

import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.ShouldMatchers

class MostFriendsSpec extends FlatSpec 
with ShouldMatchers 
with BeforeAndAfterAll { 

  val mostFriends = new MostFriends

  override def beforeAll(configMap: Map[String, Any]) { 

    mostFriends startTracking

  }

  "A MostFriends" should "have tweets" in { 

    mostFriends.tweets.size should not equal(0)

  }
}
