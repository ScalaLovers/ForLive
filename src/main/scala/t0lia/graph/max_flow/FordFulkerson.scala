package t0lia.graph.max_flow

import scala.collection.mutable

class FordFulkerson(_V: Integer,
                    _marked: mutable.Map[Integer, Boolean],
                    _edgeTo: mutable.Map[Integer, FlowEdge],
                    var _value: Double) {

  def this(G: FlowNetwork, s: Integer, t: Integer) = {
    this(G.V, mutable.Map[Integer, Boolean](), mutable.Map[Integer, FlowEdge](), 0.0)
    _value = excess(G, t)

    while (hasAugmentingPath(G, s, t)) {
      var bottle = Double.PositiveInfinity
      var v1 = t
      while (v1 != s) {
        bottle = Math.min(bottle, _edgeTo(v1).residualCapacityTo(v1))
        v1 = _edgeTo(v1).other(v1)
      }
      // augment flow
      var v2 = t
      while (v2 != s) {
        _edgeTo(v2).addResidualFlowTo(v2, bottle)
        v2 = _edgeTo(v2).other(v2)
      }
      _value = _value + bottle
    }
  }

  def value = _value

  def inCut(v: Integer): Boolean = {
    _marked.contains(v)
  }

  def excess(G: FlowNetwork, v: Integer): Double = {
    var excess = 0.0
    for (e: FlowEdge <- G.adj(v)) {
      if (v == e.from) excess = excess - e.flow
      else excess = excess + e.flow
    }
    excess
  }

  def hasAugmentingPath(G: FlowNetwork, s: Integer, t: Integer): Boolean = {
    _edgeTo.clear()
    _marked.clear()

    // breadth-first search
    val queue = new mutable.Queue[Integer]()
    queue.enqueue(s)

    _marked(s) = true
    while (queue.nonEmpty && !_marked.contains(t)) {
      val v = queue.dequeue()

      for (e <- G.adj(v)) {
        val w = e.other(v)

        // if residual capacity from v to w
        if (e.residualCapacityTo(w) > 0) {
          if (!_marked.contains(w)) {
            _edgeTo(w) = e
            _marked(w) = true
            queue.enqueue(w)
          }
        }
      }
    }

    // is there an augmenting path?
    _marked.contains(t)
  }
}
