package t0lia.graph.max_flow

class FlowEdge(v: Integer, w: Integer, c: Double) {

  private val _v = v // from
  private val _w = w // to
  private val _capacity = c // capacity
  private var _flow = 0d // flow


  def from = _v
  def to = _w
  def capacity = _capacity
  def flow = _flow

  def other(vertex: Integer): Integer = {
    if (vertex == _v) {
      _w
    } else {
      _v
    }
  }

  def residualCapacityTo(vertex: Integer): Double = {
    if (vertex == _v) {
      _flow; // backward edge
    } else {
      _capacity - _flow // forward edge
    }
  }

  def addResidualFlowTo(vertex: Integer, delta: Double): Unit = {
    if (vertex == _v) _flow -= delta; // backward edge
    else if (vertex == _w) _flow += delta; // forward edge

    // round flow to 0 or capacity if within floating-point precision
    if (Math.abs(_flow) <= 1E-10)
      _flow = 0
    if (Math.abs(_flow - _capacity) <= 1E-10)
      _flow = _capacity
  }

  override def toString: String = {
    v + "->" + w + " " + flow + "/" + capacity;
  }

}
