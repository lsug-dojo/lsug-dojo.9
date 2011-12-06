package lsug

import java.util.Date

import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.scalatest.mock.MockitoSugar
import org.scalatest.BeforeAndAfter
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EmailNotifierSpec extends WordSpec
  with MockitoSugar with BeforeAndAfter {

  var mailer: Mailer = _
  var tweetStream: TweetStream = _
  var emailNotifier: EmailNotifier = _

  before {
    mailer = mock[Mailer]
    tweetStream = mock[TweetStream]
    emailNotifier = new EmailNotifier(mailer)
  }

  "An EmailNotifier" should {
    val emailAddress = "abc@xyz.com"

    "should send an email when the search has a new match" in {
      val testTweetStream = new TweetStream(null, null, null) {
        override def addListener(listenerFunction: (Tweet) => Unit, errorHandler: (Exception) => Unit) = {
          listenerFunction(new Tweet(new Date, "author", "text", 0))
          this
        }
      }
      emailNotifier.notify(emailAddress, testTweetStream)

      verify(mailer).sendEmail("text");
    }

  }

}