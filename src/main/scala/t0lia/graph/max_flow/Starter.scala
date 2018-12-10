package t0lia.graph.max_flow

object Starter {

  def main(args: Array[String]): Unit = {
    // create flow network with V vertices and E edges
    val V = 3
    val E = 7
    val s = 0
    val t = V - 1
    val G = new FlowNetwork(V, E)
    System.out.println(G)

    // compute maximum flow and minimum cut
    val maxflow = new FordFulkerson(G, s, t)
    System.out.println("Max flow from " + s + " to " + t)

    for (v <- 0 until G.V) {
      for (e <- G.adj(v)) {
        if ((v == e.from) && e.flow > 0)
          System.out.println("   " + e)
      }
    }

    // print min-cut
    System.out.print("Min cut: ")
    for (v <- 0 until  G.V) {
      if (maxflow.inCut(v)) System.out.print(v + " ")
    }
    System.out.println()

    System.out.println("Max flow value = " + maxflow.value)
  }

}
