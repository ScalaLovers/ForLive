package t0lia.graph.max_flow

import scala.collection.mutable

class FlowNetwork(_V: Integer, var _E: Integer, _adj: mutable.Map[Integer, mutable.Set[FlowEdge]]) {

  def V = _V
  def E = _E
  def adj = _adj

  def this(V: Integer) = {
    this(V, 0, mutable.Map[Integer, mutable.Set[FlowEdge]]())
    for (c <- 0 until V) {
      _adj.put(c, mutable.Set[FlowEdge]())
    }
  }

  def addEdge(e: FlowEdge): Unit = {
    _adj(e.from) += e
    _adj(e.to) += e
    _E = _E + 1
  }

  def this(V: Integer, E: Integer) = {
    this(V)
    val random = new scala.util.Random

    for (_ <- 0 until E) {
      val vv = random.nextInt(V)
      val ww = random.nextInt(V)
      val capacity = random.nextInt(100)
      addEdge(new FlowEdge(vv, ww, capacity))
    }
  }

  override def toString(): String = {
    val s = new mutable.StringBuilder()
    s.append(_V + " " + _E + "\n")
    for (v <- 0 until _V) {
      s.append(v + ":  ")
      for (e <- _adj(v)) {
        if (e.to != v) s.append(e + "  ")
      }
      s.append("\n")
    }
    s.toString()
  }
}
