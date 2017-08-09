package com.ikempf._11_2_gcounter

import org.scalatest.{FlatSpec, Matchers}

class GCounterTest extends FlatSpec with Matchers {

  "Increment" should "increment given machine's amount" in {
    // Given
    val counter = GCounter(Map("m1" -> 5, "m2" -> 1))

    // When
    val newCounter = counter.increment("m1", 2)

    // Then
    newCounter should equal(GCounter(Map("m1" -> 7, "m2" -> 1)))
  }

  it should "add an unknown machine" in {
    // Given
    val counter = GCounter(Map("m1" -> 5))

    // When
    val newCounter = counter.increment("m3", 2)

    // Then
    newCounter should equal(GCounter(Map("m1" -> 5, "m3" -> 2)))
  }

  "Get" should "sum all amounts" in {
    // Given
    val counter = GCounter(Map("m1" -> 7, "m2" -> 1))

    // When
    val sum = counter.get

    // Then
    sum should equal(8)
  }

  "Merge" should "merge counters" in {
    // Given
    val counter1 = GCounter(Map("m1" -> 7, "m2" -> 1))
    val counter2 = GCounter(Map("m1" -> 5, "m3" -> 2))

    // When
    val newCounter = counter1.merge(counter2)

    // Then
    newCounter should equal(GCounter(Map("m1" -> 7, "m2" -> 1, "m3" -> 2)))
  }

}