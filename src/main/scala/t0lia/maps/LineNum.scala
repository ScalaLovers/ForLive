package t0lia.maps

import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object LineNum {

  def main(args: Array[String]): Unit = {
    val source = "/Users/big-theta/Sources/experiments/ForLive/src/main/resources/input.txt"
    println(dictionary(source))
  }

  def dictionary(filename: String): Map[String, ArrayBuffer[Int]] = {
    var map = Map[String, ArrayBuffer[Int]]()

    val lines = Source.fromFile(filename).getLines().toArray

    var lineNum = 0
    for (line <- lines) {
      val mList = line.split("\\W")
      for (mot <- mList) {
        if (!map.contains(mot)) {
          val row = ArrayBuffer[Int]()
          row += lineNum
          map += (mot -> row)
        }
        else {
          if (!map(mot).contains(lineNum))
            map += (mot -> (map(mot) += lineNum))
        }
      }
      lineNum += 1
    }
    map

  }
}
