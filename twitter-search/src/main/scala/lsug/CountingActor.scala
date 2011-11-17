package lsug

import scala.actors._
class CountingActor() extends Actor {
  
  var count = 0
  
	override def act() = {
	  while (true) {
	    receive {
	      case s: Array[String] => countUp(s)
	      case _ => Nil
	    }
	  }
	}
	
	def countUp(s: Array[String]): Unit = {
	  count += 1
	  if (count > 3) {
	    println("SNAFU")
	  }
	}
}
