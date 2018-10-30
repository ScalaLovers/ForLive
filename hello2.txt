object Recursion extends App {

  def fact(n: Int): Int = {
    if (n == 0) 1
    else n * fact(n - 1)
  }

  def reverseRecursive(input: Int, pow: Int): Int = {
    if (input == 0) {
      0
    } else {
      val four = reverseRecursive(input / 10, pow - 1)
      val i = four + input % 10 * Math.pow(10, pow).intValue()
      return i
    }
  }

  def reverseIterative(input: Int): Int = {
    var i = input
    var acc = 0
    while (i > 0) {
      val module = i % 10
      acc = acc * 10 + module
      i = i / 10
    }
    acc
  }

  println(reverseRecursive(4325, 3))
  println(reverseIterative(4325))

}
