package com.ikempf._7_1_folds

import org.scalatest.{FlatSpec, Matchers}

class ReflectingFoldsTest extends FlatSpec with Matchers {

  "reflectiveFoldLeft" should "reverse given list" in {
    ReflectingFolds.foldLeft(List(1, 2, 3, 4)) should equal(List(1, 2, 3, 4))
  }

  "reflectiveFoldRight" should "not modify given list" in {
    ReflectingFolds.foldLeft(List(1, 2, 3, 4)) should equal(List(4, 3, 2, 1))
  }

}