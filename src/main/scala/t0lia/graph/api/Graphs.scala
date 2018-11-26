package t0lia.graph.api

import scala.collection.mutable.{Map, Set}
import scala.io.Source

object Graphs {

  case class Node(name: String, adjacent: Set[String]) {

    override def toString(): String = {
      name + "-" + adjacent.mkString("-")
    }
  }


  def graphFromFile(filename: String): Map[String, Node] = {
    val lines = Source.fromFile(filename).getLines().toArray
    val map = Map[String, Node]()

    for (line <- lines) {
      val nodesInLine = line.split("-")
      if (!map.contains(nodesInLine(0))) {
        val node = Node(nodesInLine(0), Set[String](nodesInLine(1)))
        map(nodesInLine(0)) = node
      }
      else {
        val nodes = map(nodesInLine(0)).adjacent
        map(nodesInLine(0)) = Node(nodesInLine(0), nodes + nodesInLine(1))
      }
    }
    map
  }

  def printGraph(graph: Map[String, Node]): Unit = {
    for (nodeName <- graph.keys.toSeq.sorted) {
      val node = graph(nodeName)
      println(node)
    }

  }

}
