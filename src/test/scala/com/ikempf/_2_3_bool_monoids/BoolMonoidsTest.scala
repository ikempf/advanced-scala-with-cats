package com.ikempf._2_3_bool_monoids

import com.ikempf._2_3_bool_monoids.BoolMonoids.orMonoid
import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers

class BoolMonoidsTest extends FlatSpec with Checkers {

  val boolGen = Gen.oneOf(true, false)

  "OrMonoid" should "satisfy monoid laws" in {
    check(MonoidLaws(boolGen)(orMonoid))
  }

  "AndMonoid" should "satisfy monoid laws" in {
    check(MonoidLaws(boolGen)(BoolMonoids.andMonoid))
  }


}