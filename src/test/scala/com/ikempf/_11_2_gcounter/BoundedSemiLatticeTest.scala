package com.ikempf._11_2_gcounter

import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers

import scala.util.Try

class BoundedSemiLatticeTest extends FlatSpec with Checkers {

  val intGen = Gen.choose(0, 1000)
  val stringGen = Gen.alphaNumStr
  val setGen = Gen.listOf(stringGen).map(_.toSet)

  val invalidStringInstance: BoundedSemiLattice[String] = new BoundedSemiLattice[String]{
    override def empty: String = ""

    override def combine(x: String, y: String): String = x + y
  }

  "StringInstance" should "satisfy bounded semi lattice laws" in {
    Try(check(new BoundedSemiLatticeLaws[String](stringGen)(invalidStringInstance).all))
        .map(_ => fail("Should no tbe commutative"))
  }

  "IntInstance" should "satisfy bounded semi lattice laws" in {
    check(new BoundedSemiLatticeLaws[Int](intGen)(BoundedSemiLattice.intInstance).all)
  }

  "SetInstance" should "satisfy bounded semi lattice laws" in {
    check(new BoundedSemiLatticeLaws[Set[String]](setGen)(BoundedSemiLattice.setInstance()).all)
  }

}