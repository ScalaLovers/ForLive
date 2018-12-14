package t0lia.tree

import scala.collection.mutable

object Hello {

  class Node(var value: Integer, var left: Node, var right: Node) {

    def append(n: Integer): Unit = {
      if (n > value) {
        if (left == null) {
          left = new Node(n, null, null)
        } else {
          left.append(n)
        }
      } else {
        if (right == null) {
          right = new Node(n, null, null)
        } else {
          right.append(n)
        }
      }

    }
  }

  class Tree(root: Node) {

    def this(n: Integer) = {
      this(new Node(n, null, null))
    }


    def add(n: Integer): Unit = {
      root.append(n)
    }

    def traverse(): mutable.ListBuffer[Integer] = {
      traverse(mutable.ListBuffer[Integer](), root)
    }

    def traverse(result: mutable.ListBuffer[Integer], current: Node): mutable.ListBuffer[Integer] = {
      if (current.left != null)
        traverse(result, current.left)
      result += current.value
      if (current.right != null)
        traverse(result, current.right)
      result
    }

    override def toString = {
      root.value.toString
    }

  }

  def main(args: Array[String]): Unit = {
    val tree = new Tree(3)
    tree.add(4)
    tree.add(2)
    tree.add(5)
    tree.add(1)
    println(tree.traverse())
  }

}

