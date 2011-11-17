package lsug

import scala.actors._
class FilteringActor(val children : List[Actor], val terms : List[String]) extends Actor {
  
	override def act() = {
	  while (true) {
	    receive {
	      case s: Array[String] => notifyIfProblem(s)
	      case _ =>  println("received  checking")
	    }
	  }
	}
	
	def notifyIfProblem(s: Array[String]): Unit = {
//	  println("received '" + s + "' checking")
	  if (IsProblemOnTheLine(s)) {
	    children.foreach(_!s)
	  }
	}
	
  def IsProblemOnTheLine(message: Array[String]): Boolean = {
    message.count(terms.contains(_)) > 0
  }
}

