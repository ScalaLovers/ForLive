package t0lia.coursework

import scala.collection.mutable
import scala.io.Source


object Project1 {
  def main(args: Array[String]): Unit = {

    //    var t1 = frequency("/Users/natalia/Desktop/Project/t1.txt")
    //    var t2 = frequency("/Users/natalia/Desktop/Project/t2.txt")
    //    var t3 = frequency("/Users/natalia/Desktop/Project/t3.txt")
    //    var t4 = frequency("/Users/natalia/Desktop/Project/t4.txt")
    var t5 = frequency("/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/t2.txt")


    var g = frequency("/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/gen-voc.txt")

    //    println("t1")
    //    execute(t1,g)
    //    println("t2")
    //    execute(t2,g)
    //    println("t3")
    //    execute(t3,g)
    //    println("t4")
    //    execute(t4,g)
    println("t5")
    execute(t5, g)
  }


  def execute(t: mutable.Map[String, Int], g: mutable.Map[String, Int]) {
    var mapscores = scores(t, g)
    mapscores.toSeq
      .sortWith(_._2 > _._2).slice(0, 20)
      .foreach(word => printf("%1$15s %2$.8f%n", word._1, word._2))
  }


  def getWordsList(line: String): Array[String] = {
//    return line.split("[\\W0-9]+")
    return line.split("\\W")
  }


  def frequency(filepath: String): mutable.Map[String, Int] = {
    var map = mutable.Map[String, Int]()
    val lines = Source.fromFile(filepath).getLines().toArray
    val words = lines.flatMap(line => getWordsList(line)).map(word => word.toLowerCase)

    for (word <- words) {

      if (map.contains(word))
        map(word) = map(word) + 1

      else
        map(word) = 1
    }

    return map
  }


  def scores(map: mutable.Map[String, Int], voc: mutable.Map[String, Int]): mutable.Map[String, Double] = {
    var mapscores = mutable.Map[String, Double]()
    for (key <- map.keys) {
      if (map.contains(key) && voc.contains(key) && map(key) > 3) {
        mapscores(key) = map(key).toDouble / voc(key)
      }
    }
    return mapscores
  }


}

