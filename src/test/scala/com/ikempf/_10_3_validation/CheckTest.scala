package com.ikempf._10_3_validation

import org.scalatest.{FlatSpec, Matchers}
import cats.instances.string._
import cats.instances.list._

class CheckTest extends FlatSpec with Matchers {

  "Single check" should "verify condition condition" in {
    Check[String, Int]("Invalid", i => i > 0).verify(5) should equal(Right(5))
    Check[String, Int]("Invalid", i => i > 0).verify(-1) should equal(Left("Invalid"))
  }

  "Multiple And checks" should "verify all conditions" in {
    // Given
    val maxCheck = Check[List[String], Int](List("Too low"), i => i > 0)
    val minCheck = Check[List[String], Int](List("Too great"), i => i < 10)
    val notPair = Check[List[String], Int](List("Must not be pair"), i => i % 2 != 0)

    val combined = maxCheck.and(minCheck).and(notPair)

    // When / Then
    combined.verify(5) should equal(Right(5))
    combined.verify(4) should equal(Left(List("Must not be pair")))
    combined.verify(11) should equal(Left(List("Too great")))
    combined.verify(-1) should equal(Left(List("Too low")))
    combined.verify(-2) should equal(Left(List("Too low", "Must not be pair")))
  }

  "Multiple Or checks" should "verify at least one condition" in {
    // Given
    val maxCheck = Check[List[String], Int](List("Too low"), i => i > 0)
    val notPair = Check[List[String], Int](List("Must not be pair"), i => i % 2 != 0)

    val combined = maxCheck.or(notPair)

    // When / Then
    combined.verify(5) should equal(Right(5))
    combined.verify(-1) should equal(Right(-1))
    combined.verify(2) should equal(Right(2))
    combined.verify(-2) should equal(Left(List("Too low", "Must not be pair")))
  }

}