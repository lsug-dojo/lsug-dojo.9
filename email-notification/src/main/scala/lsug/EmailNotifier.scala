package lsug

class EmailNotifier(mailer: Mailer) {

  def notify(email: String, tweetStream: TweetStream) {
    tweetStream.addListener(tweet => mailer.sendEmail(tweet.text))
  }

}