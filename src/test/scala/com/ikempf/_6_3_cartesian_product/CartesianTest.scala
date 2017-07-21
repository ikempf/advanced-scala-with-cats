package com.ikempf._6_3_cartesian_product

import org.scalatest.{FlatSpec, Matchers}
import cats.instances.option._
import cats.instances.list._

class CartesianTest extends FlatSpec with Matchers {

  "product" should "handle some" in {
    // Given
    val option1 = Option("ok1")
    val option2 = Option("ok2")

    // When
    val optTuple = Cartesian.product(option1, option2)

    // Then
    optTuple should equal(Option(("ok1", "ok2")))
  }

  it should "handle none" in {
    // Given
    val option1 = Option("ok1")
    val option2 = None

    // When
    val optTuple = Cartesian.product(option1, option2)

    // Then
    optTuple should equal(None)
  }

  it should "handle lists" in {
    // Given
    val l1 = List(1, 2)
    val l2 = List(7, 8, 9)

    // When
    val lTuple = Cartesian.product(l1, l2)

    // Then
    lTuple should equal(List((1, 7), (1, 8), (1, 9), (2, 7), (2, 8), (2, 9)))
  }

}