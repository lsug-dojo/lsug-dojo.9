package lsug

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import twitter4j._

object WeatherMonitor extends App { 
  import twitter._
  
  // TODO use a NamedLocation instead
  lazy val london = BoundingBoxLocation(BoundingBox((-1, 51.0), (1, 53.0)))
    
  val Array(place) = args
  def getUrlText(url: String): String ={
    import java.net._
    import java.io._
    import org.apache.commons.io._
    
    //val in = new BufferedReader(new InputStreamReader(.openStream()))
    val body = IOUtils.toString(new URL(url), "UTF-8")
    println(body)
    body
  }
  
  /*
  object Point {
    def unapply(
  */
  def getJsonStuff(url: String): Unit = { 
    import net.liftweb.json.JsonParser._ 
    
    val json = parse(getUrlText(url))
    println(json \ "bounding_box")
    println(json \ "bounding_box" \ "coordinates")
    //println((json \\ "coordinates"))
  }
  
  val baseURL = "http://api.twitter.com/1/geo/search.json?query=" + place
  getUrlText(baseURL)
  getJsonStuff(baseURL)
  
  //val config = TwitterConfig.withUserAndPassword("lsugdojo", "...")
  val config = TwitterConfig.withOAuth(
    twitterConsumerKey = "MzgtrYGHH4r1uUNIB3rgg",
    twitterConsumerSecret = "LqF2oihPeLxGJQddQG3bRzdZvih5Qfa36Z5oTA",
    twitterAccessToken = "436077745-6BcpjUb63eghPD0tMQqCDEWyeSkduTOsAWjb55lq",
    twitterAccessTokenSecret = "2pub4uncdyFbSquYKMchlJZ03RwafsCn0OoYBqTaQA"
  )
  
  def toRegexWords(as: Array[String]) =
    as.
      map(s => "\\b" + s + "\\b").
      map(_.r)
    
  val goodWeatherTerms = toRegexWords(Array(
    "sunny", "warm", "dry", //"clear", 
    "bright sky"
  ))
  val badWeatherTerms = toRegexWords(Array(
    "cloudy", "rain", "cold", "freezing",
    """(-?[\d+]+(?:.\d+)?)\s*(f(?:arenheit)?|c(?:elcius)?)"""
    //"""\d+c"""
  ))
  
  val stream = config.createStream
  stream.listen(tweet => {
    val text = tweet.getText.toLowerCase
    val foundGood = goodWeatherTerms.filter(_.findFirstIn(text) != None)
    val foundBad = badWeatherTerms.filter(_.findFirstIn(text) != None)
    
    if (!foundGood.isEmpty || !foundBad.isEmpty) {// || text.contains("temperature")) {
      println(tweet.getText + " @ " + Option(tweet.getPlace).map(_.getName).getOrElse("?"))
    //println(tweet.getText + " @ " + Option(tweet.getPlace).map(_.toString).getOrElse("?"))
    }
  }).at(london)
    
  //val temperatureCelsiusRx = """temperature (-?[\d+](?:.\d+)?) (F(?:arenheit)?|C(?:elcius)?)""".r
}

