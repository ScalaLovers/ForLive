package t0lia

/**
  *
  * Пример с разворотом числа. Есть две разновидности рекурсии: хвостовая и головная. Различаются они тем где находится
  * вызов рекурсивного метода.
  * (https://ru.wikipedia.org/wiki/%D0%A5%D0%B2%D0%BE%D1%81%D1%82%D0%BE%D0%B2%D0%B0%D1%8F_%D1%80%D0%B5%D0%BA%D1%83%D1%80%D1%81%D0%B8%D1%8F)
  *
  * !============================
  * Пример головной рекурсии. Сначала вызывается метод, затем делаются вычисления
  *
  * def factHead(n: Int): Int = {
  *  if (n == 0) 1
  *  else n * factHead(n - 1)
  *}
  * Здесь при условии что n !=0 сначала вызавается factHead, а затем результат умножается на n
  *
  * !============================
  * Пример хвостовой рекурсии. Сначала производятся вычисления, затем вызывается метод
  *def factTail(n: Int, acc: Int): Int = {
  *  if (n == 0) acc
  *  else factTail(n - 1, acc * n)
  *}
  * В примере factTail вызывается в самом конце после того как посчитаются n - 1, acc * n и подставятся в аргументы
  *
  * Также рекурсия может быть сразу и головной и хвостовой. Инструкции, которые предшествую вызову функции выполняются
  * на прямом ходе в прямом порядке, которые идут после вызова функции выполняются на обртном ходе (когда стек раскручивается)
  * и идут в обратном порядке.В примере printExample на прямом ходе число уменьшается на обратном растет
  *
  * !!! Nota Bene !!!
  * Хвостовая рекурсия может быть заменена равноценным алгоритмом с использованием цикла. В некоторых языках программирования
  * это делается автоматически. Головную рекурсию сложно представить в виде цикла (приходится использовать другой алгоритм)
  *
  */
object ReverseNumber {
  def main(args: Array[String]): Unit = {
    println(">> Выводит значения от 3 до 0 и обратно")
    printExample(3)

    println(">> Вычисляет факториал при помощи головной рекурсии")
    println(factHead(5))

    println(">> Вычисляет факториал при помощи хвостовой рекурсии")
    println(factTail(5, 1))

    println(">> реверс числа при помощи хвостовой рекурсии")
    println(reverseHeadRecursion(4325, 3))

    println(">> реверс числа при помощи головной рекурсии")
    println(reverseTailRecursion(4325, 0))

    println(">> реверс числа с использовнием цикла")
    println(reverseIterative(4325))
  }


  def printExample(n: Int): Void = {
    if (n > 0) {
      println("прямой ход:   " + n)
      printExample(n - 1)
      println("обратный ход: " + n)
    }
    null
  }

  def factHead(n: Int): Int = {
    if (n == 0) 1
    else n * factHead(n - 1)
  }

  def factTail(n: Int, acc: Int): Int = {
    if (n == 0) acc
    else factTail(n - 1, acc * n)
  }

  /**
    * С использованием цикла. Реализуется довольно легко т.к. работа с числом делается с правого края (для работы с
    * левым краем надо знать позиция ака степень десятки на которую умножаешь)
    *
    */
  def reverseIterative(input: Int): Int = {
    var i = input
    var accumulator = 0
    while (i > 0) {
      val module = i % 10
      accumulator = accumulator * 10 + module
      i = i / 10
    }
    accumulator
  }

  /**
    * Хвостовая рекурсия аналогична алгоритму с использованием цикла. Работает с числом от правого края.
    */
  def reverseTailRecursion(inputNumber: Int, accumulator: Int): Int = {
    if (inputNumber == 0) {
      return accumulator
    }
    val module = inputNumber % 10
    val reverseAfter = accumulator * 10 + module
    val tail = inputNumber / 10
    reverseTailRecursion(tail, reverseAfter)
  }


  /**
    * Для реверса числа головная рекурсия работает плохо т.к. работает с числом на обратном проходе и заходит с левого
    * края. Для этого приходится городить тему с десяткой в степени
    */
  def reverseHeadRecursion(inputNumber: Int, pow: Int): Int = {
    if (inputNumber == 0) {
      0
    } else {
      val four = reverseHeadRecursion(inputNumber / 10, pow - 1)
      val result = four + inputNumber % 10 * Math.pow(10, pow).intValue()
      result
    }
  }


}

