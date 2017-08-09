package com.ikempf._11_2_gcounter

import com.ikempf._2_3_bool_monoids.{Monoid, MonoidLaws}
import org.scalacheck.{Gen, Prop}
import org.scalacheck.Prop.forAll

object BoundedSemiLatticeLaws {

  def apply[A](gen: Gen[A])(implicit m: Monoid[A]): Prop =
    new BoundedSemiLatticeLaws(gen)(m).all

}

class BoundedSemiLatticeLaws[A](gen: Gen[A])(implicit m: Monoid[A]) {

  val commutativity = forAll(gen, gen)((a, b) =>
    m.combine(a, b) == m.combine(b, a)
  )

  val all = new MonoidLaws[A](gen).all && commutativity

}