package com.ikempf._4_6_writer_monad

import com.ikempf._4_6_writer_monad.Factorial.{factorial, factorialW}
import org.scalatest.{FlatSpec, FunSuite, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class FactorialTest extends FlatSpec with Matchers {

  "Parallel factorial with writer" should "print logs in sequence" in {
    //Await.result(Future.sequence(Vector(
    //  Future(factorial(3)),
    //  Future(factorial(3))
    //)), 5.seconds)

    val res = Await.result(Future.sequence(Vector(
      Future(factorialW(3)),
      Future(factorialW(3))
    )), 5.seconds)

    res(0).run._1 should equal(List("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6"))
    res(1).run._1 should equal(List("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6"))
  }

}
