package com.ikempf._7_1_folds

import com.ikempf._7_1_folds.FoldSubstitues._
import org.scalatest.{FlatSpec, Matchers}

class FoldSubstituesTest extends FlatSpec with Matchers {

  "foldMap" should "map" in {
    foldMap(List(1, 2, 3))(_.toString) should equal(List("1", "2", "3"))
  }

  "foldFlatMap" should "flatMap" in {
    foldFlatMap(List(1, 2, 3))(List.fill(2)(_)) should equal(List(1, 1, 2, 2, 3, 3))
  }

  "foldFilter" should "filter" in {
    foldFilter(List(1, 2, 3))(_ != 2) should equal(List(1, 3))
  }

  "foldSum" should "sum" in {
    foldSum(List(1, 2, 3)) should equal(6)
  }

}