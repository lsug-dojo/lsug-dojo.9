package code.comet

import net.liftweb.http.CometActor
import lsug.Tweet

class TwitterSearch extends CometActor {
  def render = null

  val results:List[Tweet] = List[Tweet]()
}