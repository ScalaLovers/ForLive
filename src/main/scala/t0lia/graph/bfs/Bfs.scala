package t0lia.graph.bfs

import t0lia.graph.api.Graphs

import scala.collection.mutable
import scala.collection.mutable.Queue

object Bfs {

  val marked = mutable.Map[String, Boolean]()
  val edgeTo = mutable.Map[String, String]()
  var s = "0"

  def main(args: Array[String]): Unit = {

    bfs(Graphs.graphFromFile("/Users/big-theta/Sources/experiments/ForLive/src/main/resources/graph/bfs/input.txt"), s)
    println(pathTo("3"))
    println(pathTo("5"))
    println(pathTo("4"))

  }

  def bfs(graph: mutable.Map[String, Graphs.Node], s: String): Unit = {
    val queue = Queue[String]()
    queue.enqueue(s)
    marked(s) = true
    while (queue.nonEmpty) {
      val v = queue.dequeue()
      for (w <- graph(v).adjacent.toSeq.sorted) {
        if (!marked.contains(w)) {
          queue.enqueue(w)
          marked(w) = true
          edgeTo(w) = v
        }
      }
    }

  }

  def hasPathTo(v: String): Boolean = {
    marked.contains(v)
  }

  def pathTo(v: String): mutable.Stack[String] = {
    if (!hasPathTo(v)) {
      return null
    }
    val path = mutable.Stack[String]()
    var x = v
    while (!x.eq(s)) {
      path.push(x)
      x = edgeTo(x)
    }
    path
  }

}
