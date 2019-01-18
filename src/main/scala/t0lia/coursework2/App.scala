package t0lia.coursework2

import java.net._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


object App {
  def main(args: Array[String]): Unit = {
    val sql1 = "select r.name, r.river from river r where r.river is not null"
    val sql2 = "select r.name, r.sea from river r where r.sea in ('Atlantic ocean','Mediterranean Sea')"

    val riverToRiver = query(sql1)
    //    for (c <- riverToRiver) {
    //      println(c)
    //    }

    val riverToSea = query(sql2)
    //    for (c <- riverToSea) {
    //      println(c)
    //    }

    val chains = riverChains(riverToRiver)
    for (key <- chains.keys) {

      val chain = chains(key)
      val last = chain.last
      if (riverToSea.contains(last))
        println(key + " -> " + chain.mkString(" -> ") + " -> " + riverToSea(last))
    }
  }

  def riverChains(riverInRiver: mutable.Map[String, String]): Map[String, ArrayBuffer[String]] = {
    var map = Map[String, ArrayBuffer[String]]()

    for (source <- riverInRiver.keys) {

      var chain = ArrayBuffer[String]()

      if (!map.contains(source)) {
        chain = ArrayBuffer[String]()
      }
      else {
        chain = map(source)
      }
      // A tributary is a stream or river that flows into a larger stream or main stem (or parent) river
      var tributary = source
      while (riverInRiver.contains(tributary)) {
        // A river mouth is the part of a river where the river debouches into another river
        val mouth = riverInRiver(tributary)
        chain += mouth
        tributary = mouth
      }
      map += (source -> chain)

    }
    map

  }

  def query(sql: String): mutable.Map[String, String] = {
    val eq = URLEncoder.encode(sql, "UTF-8")

    val url = new java.net.URL("http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(url, "iso-8859-1")
    val res = mutable.Map[String, String]()
    for (line <- in.getLines) {
      val cols = line.split("\t")
      res(cols(0)) = cols(1)
    }
    in.close()
    res
  }

}

