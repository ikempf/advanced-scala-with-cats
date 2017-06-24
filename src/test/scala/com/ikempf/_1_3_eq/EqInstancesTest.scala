package com.ikempf._1_3_eq

import cats.kernel.Eq
import com.ikempf._1_1_printable.Cat
import org.scalatest.{FlatSpec, Matchers}
import EqInstances.catEq
import cats.instances.option._

class EqInstancesTest extends FlatSpec with Matchers {

  "EqCat" should "compare cats" in {
    val cat1 = Cat("Garfield", 35, "orange and black")
    val cat2 = Cat("Heathcliff", 30, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    Eq[Cat].eqv(cat1, cat1) should be(true)
    Eq[Cat].eqv(cat1, cat2) should be(false)
    Eq[Option[Cat]].eqv(optionCat1, optionCat1) should be(true)
    Eq[Option[Cat]].eqv(optionCat1, optionCat2) should be(false)
  }

}