package com.ikempf._2_3_bool_monoids

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Prop}

object MonoidLaws {
  def apply[A](gen: Gen[A])(implicit m: Monoid[A]): Prop =
    new MonoidLaws(gen)(m).all
}

class MonoidLaws[A](gen: Gen[A])(implicit m: Monoid[A]) {

  val associativity = forAll(gen, gen, gen)((a, b, c) =>
    m.combine(a, m.combine(b, c)) == m.combine(m.combine(a, b), c)
  )

  val identity = forAll(gen)(a =>
    (m.combine(a, m.empty) == a) && (m.combine(m.empty, a) == a)
  )

  val all = associativity && identity

}