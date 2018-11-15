package t0lia.coursework

import scala.collection.mutable.Map
import scala.io.Source

object TopicFinder {

  def main(args: Array[String]): Unit = {

    //    val input = "/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/t1.txt"
    //    val input = "/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/t2.txt"
    //    val input = "/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/t3.txt"
    //    val input = "/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/t4.txt"
    val input = "/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/t5.txt"
    val voc = "/Users/big-theta/Sources/experiments/ForLive/src/main/resources/coursework/gen-voc.txt"

    val inputFreq = frequency(input)
    val vocFreq = frequencyVoc(voc)

    val scores = score(inputFreq, vocFreq)


    val top20words = scores.toSeq.sortWith(_._2 > _._2).slice(0, 20).map(x => x._1)

//    println("word -> score -> freq")
    for (word <- top20words) {
      printf("%1$15s %2$f%n", word, inputFreq(word))
    }

  }

  def frequency(file: String): Map[String, Double] = {
    val map = Map[String, Int]()
    val lines = Source.fromFile(file).getLines().toArray
    val words = lines.flatMap(line => line.split("\\W")).map(w => w.toLowerCase)

    for (word <- words) {
      if (map.contains(word)) {
        map(word) = map(word) + 1
      }
      else {
        map(word) = 1
      }
    }
    val result = Map[String, Double]()
    for (key <- map.keys) {
      if (map(key) > 3) {
        result += key -> map(key).toDouble / words.length
      }
    }
    result
  }

  def frequencyVoc(file: String): Map[String, Double] = {
    val map = Map[String, Int]()
    val lines = Source.fromFile(file).getLines().toArray
    val words = lines.flatMap(line => line.split("\\W")).map(w => w.toLowerCase)

    for (word <- words) {
      if (map.contains(word)) {
        map(word) = map(word) + 1
      }
      else {
        map(word) = 1
      }
    }
    val result = Map[String, Double]()
    for (key <- map.keys) {
      result += key -> map(key).toDouble / words.length
    }
    result
  }

  def score(input: Map[String, Double], voc: Map[String, Double]): Map[String, Double] = {
    val score = Map[String, Double]()
    for (key <- input.keys) {
      if (voc.contains(key)) {
        score += key -> input(key) / voc(key)
      }
    }
    score
  }
}
