package lsug.dojo.twitter.friends

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class MostFriendsSpec extends FlatSpec with ShouldMatchers { 



  "A MostFriends" should "have tweets" in { 
    val mostFriends = new MostFriends

    mostFriends.tweets.size should not equal(0)
  }

//  it should "throw NoSuchElementException if an empty stack is popped" in { 
//    val emptyStack = new Stack[String]
//    evaluating {  emptyStack.pop() } should produce [NoSuchElementException]
//  }
}
