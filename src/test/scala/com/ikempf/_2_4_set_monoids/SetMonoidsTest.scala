package com.ikempf._2_4_set_monoids

import com.ikempf._2_3_bool_monoids.{MonoidLaws, SemigroupLaws}
import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers

class SetMonoidsTest extends FlatSpec with Checkers{

  "AddMonoid" should "satisfy monoid laws" in {
    check(MonoidLaws(Gen.listOf(Gen.alphaLowerStr).map(_.toSet))(SetMonoids.unionMonoid[String]))
  }

  "IntersectionSemigroup" should "satisfy semigroupLaws laws" in {
    check(SemigroupLaws(Gen.listOf(Gen.alphaLowerStr).map(_.toSet))(SetMonoids.intersectionSemigroup[String]))
  }

}