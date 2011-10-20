package lsug

object JourneyProblem {


  def main(args: Array[String]): Unit = {
    
    val counter = new CountingActor()
    counter.start()
    
    val initial = new FilteringActor(List(counter), List("delay"))
    initial.start()
    
    var message = Console.readLine()
    while (message != null) {
      println(message)
      val tweet : Array[String] = message.split(" ").tail
      initial ! tweet
      message = Console.readLine()
    }
  }
}



