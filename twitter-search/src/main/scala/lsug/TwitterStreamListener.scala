package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import twitter4j.{Twitter, TwitterFactory}

class TwitterStreamListener {
  val cb = new ConfigurationBuilder();
  val build: Configuration = cb.setDebugEnabled(true).setUser("lsugoct").setPassword("Rfsd5819").build()
  val twitterFactory: TwitterFactory = new TwitterFactory(build)
  val twitter: Twitter = twitterFactory.getInstance()

}