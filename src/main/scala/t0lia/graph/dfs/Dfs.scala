package t0lia.graph.dfs

import t0lia.graph.api.Graphs

import scala.collection.mutable

object Dfs {

  val marked = mutable.Map[String, Boolean]()
  val edgeTo = mutable.Map[String, String]()
  var s = "0"

  def main(args: Array[String]): Unit = {

    dfs(Graphs.graphFromFile("/Users/big-theta/Sources/experiments/ForLive/src/main/resources/graph/dfs/input.txt"), s)
    println(pathTo("3"))
    println(pathTo("2"))
    println(pathTo("5"))

  }

  def dfs(graph: mutable.Map[String, Graphs.Node], v: String): Unit = {
    marked(v) = true
    for (w <- graph(v).adjacent.toSeq.sorted.reverse) {
      if (!marked.contains(w)) {
        dfs(graph, w)
        edgeTo(w) = v
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
