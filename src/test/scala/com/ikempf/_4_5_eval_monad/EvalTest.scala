package com.ikempf._4_5_eval_monad

import com.ikempf._4_5_eval_monad.EvalFunctions.foldRight
import org.scalatest.{FlatSpec, Matchers}

class EvalTest extends FlatSpec with Matchers {

  "foldRight" should "not stack overflow" in {
    // Given
    val ints = List.fill(50000)(1)

    // When
    val sum = foldRight(ints, 0)(_ + _)

    // Then
    sum.value should equal(50000)
  }

}