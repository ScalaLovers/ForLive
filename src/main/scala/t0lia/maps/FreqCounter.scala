package t0lia.maps

import scala.collection.mutable.Map

object FreqCounter {
  def main(args: Array[String]): Unit = {
    val source = "cicciba bbbbad fghdsgs"
    println(frequency_char(source))

  }

  def frequency_char(a: String): Map[Char, Int] = {
    val map = Map[Char, Int]()
    for (c <- a) {
      if (map.contains(c)) {
        map(c) = map(c) + 1
      }
      else {
        map(c) = 1
      }
    }
    map
  }
}
