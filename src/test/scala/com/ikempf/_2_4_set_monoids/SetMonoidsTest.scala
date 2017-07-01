package com.ikempf._2_4_set_monoids

import com.ikempf._2_3_bool_monoids.{MonoidLaws, SemigroupLaws}
import com.ikempf._2_4_set_monoids.SetMonoids.{intersectionSemigroup, unionMonoid}
import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers

class SetMonoidsTest extends FlatSpec with Checkers {

  private val stringsGen = Gen.listOf(Gen.alphaLowerStr).map(_.toSet)

  "AddMonoid" should "satisfy monoid laws" in {
    check(MonoidLaws(stringsGen)(unionMonoid[String]))
  }

  "IntersectionSemigroup" should "satisfy semigroupLaws laws" in {
    check(SemigroupLaws(stringsGen)(intersectionSemigroup[String]))
  }

}