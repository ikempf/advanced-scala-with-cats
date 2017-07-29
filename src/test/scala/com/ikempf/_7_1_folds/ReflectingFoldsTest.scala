package com.ikempf._7_1_folds

import org.scalatest.{FlatSpec, Matchers}

class ReflectingFoldsTest extends FlatSpec with Matchers {

  "foldLeft" should "reverse given list" in {
    ReflectingFolds.foldLeft(List(1, 2, 3, 4)) should equal(List(1, 2, 3, 4))
  }

  "foldRight" should "not modify given list" in {
    ReflectingFolds.foldRight(List(1, 2, 3, 4)) should equal(List(1, 2, 3, 4))
  }

}