package com.ikempf._4_6_writer_monad

import cats.data.Writer
import cats.instances.list.catsKernelStdMonoidForList

object Factorial {

  def slowly[A](body: => A): A =
    try body finally Thread.sleep(100)

  def factorial(n: Int): Int = {
    val ans = slowly(if (n == 0) 1 else n * factorial(n - 1))
    println(s"fact $n $ans")
    ans
  }

  def factorialW(n: Int): Writer[List[String], Int] = {
    val ans = slowly(if (n == 0)
      Writer.value[List[String], Int](1)
    else
      factorialW(n - 1).map(_ * n))
    ans.flatMap(i => Writer.tell(List(s"fact $n $i")).map(_ => i))
  }

}