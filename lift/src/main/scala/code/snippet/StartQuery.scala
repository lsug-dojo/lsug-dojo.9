package code.snippet

import net.liftweb.http.js.JsCmds.SetValById
import net.liftweb.actor.LiftActor
import net.liftweb.http.{ListenerManager, SHtml}
import scala.Predef._
import lsug.{TweetStream, Tweet}

object  StartQuery {
    ​
    /**
     * The render method in this case returns a function
     * that transforms NodeSeq => NodeSeq.  In this case,
     * the function transforms a form input element by attaching
     * behavior to the input.  The behavior is to send a message
     * to the ChatServer and then returns JavaScript which
     * clears the input.
     */
    def render = SHtml.onSubmit(query => {
      TweetServer ! query
      SetValById("searchtextbox", "")
    })

  }

object TweetServer extends LiftActor with ListenerManager{
  var tweets = List[Tweet]()
  protected def createUpdate = tweets

  def handletweet(tweet:Tweet){
    tweets = tweets :+ tweet;
    updateListeners()
  }
  
  override def lowPriority = {
    case query: String => new TweetStream(query, "lsugoct", "Rfsd5819").addListener(handletweet).start
  }
}