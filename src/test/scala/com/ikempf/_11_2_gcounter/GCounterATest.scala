package com.ikempf._11_2_gcounter

import org.scalatest.{FlatSpec, Matchers}


class GCounterATest extends FlatSpec with Matchers {

  "Increment" should "increment given machine's amount" in {
    // Given
    val counter = GCounterA(Map("m1" -> 5, "m2" -> 1))

    // When
    val newCounter = counter.increment("m1", 2)(Monoid.intInstance)

    // Then
    newCounter should equal(GCounterA(Map("m1" -> 7, "m2" -> 1)))
  }

  it should "add an unknown machine" in {
    // Given
    val counter = GCounterA(Map("m1" -> 5))

    // When
    val newCounter = counter.increment("m3", 2)(Monoid.intInstance)

    // Then
    newCounter should equal(GCounterA(Map("m1" -> 5, "m3" -> 2)))
  }

  "Get" should "sum all amounts" in {
    // Given
    val counter = GCounterA(Map("m1" -> 7, "m2" -> 1))

    // When
    val sum = counter.get(Monoid.intInstance)

    // Then
    sum should equal(8)
  }

  "Merge" should "merge counters" in {
    // Given
    val counter1 = GCounterA(Map("m1" -> 7, "m2" -> 1))
    val counter2 = GCounterA(Map("m1" -> 5, "m3" -> 2))

    // When
    val newCounter = counter1.merge(counter2)(BoundedSemiLattice.intInstance)

    // Then
    newCounter should equal(GCounterA(Map("m1" -> 7, "m2" -> 1, "m3" -> 2)))
  }

}