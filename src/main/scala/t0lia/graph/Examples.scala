package t0lia.graph

import t0lia.graph.api.Graphs

object Examples {
  def main(args: Array[String]): Unit = {
    val graph = Graphs.graphFromFile("/Users/big-theta/Sources/experiments/ForLive/src/main/resources/graph/bfs/input.txt")
    Graphs.printGraph(graph)
  }
}
