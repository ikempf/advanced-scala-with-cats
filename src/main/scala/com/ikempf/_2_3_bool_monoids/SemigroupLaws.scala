package com.ikempf._2_3_bool_monoids

import org.scalacheck.{Gen, Prop}
import org.scalacheck.Prop.forAll

object SemigroupLaws {
  def apply[A](gen: Gen[A])(implicit m: Semigroup[A]): Prop =
    new SemigroupLaws(gen)(m).associativity
}

private class SemigroupLaws[A](gen: Gen[A])(implicit m: Semigroup[A]) {

  private val associativity = forAll(gen, gen, gen)((a, b, c) =>
    m.combine(a, m.combine(b, c)) == m.combine(m.combine(a, b), c)
  )

}