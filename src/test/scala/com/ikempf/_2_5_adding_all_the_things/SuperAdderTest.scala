package com.ikempf._2_5_adding_all_the_things

import cats.instances.int._
import cats.instances.option._
import org.scalatest.{FlatSpec, Matchers}

class SuperAdderTest extends FlatSpec with Matchers {

  "add List[Int]" should "sum int list" in {
    SuperAdder.add(List(1, 5, 9, 3)) should equal(18)
  }

  "add List[Option[Int]]" should "sum optional int list" in {
    SuperAdder.add(List(Some(1), Some(5), None, Some(3))) should equal(Some(9))
  }

  "add List[Order]" should "sum order list" in {
    SuperAdder.add(List(Order(0, 5), Order(3, 1), Order(9, 13))) should equal(Order(12, 19))
  }

}