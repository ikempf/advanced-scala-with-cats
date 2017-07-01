package com.ikempf._3_5_functors

import cats.Functor
import org.scalacheck.{Gen, Prop}

import scala.language.higherKinds

object FunctorLaws {
  def apply[A, B, C, F[_]](fGen: Gen[A => B], gGen: Gen[B => C], faGen: Gen[F[A]])(implicit F: Functor[F]): Prop = {
    new FunctorLaws[A, B, C, F](fGen, gGen, faGen).all
  }
}

private class FunctorLaws[A, B, C, F[_]](fGen: Gen[A => B], gGen: Gen[B => C], faGen: Gen[F[A]])(implicit F: Functor[F]) {

  private val identityLaw = Prop.forAll(faGen) {
    fa => F.map(fa)(identity) == fa
  }

  private val composition = Prop.forAll(fGen, gGen, faGen) {
    (f, g, fa) => F.map(F.map(fa)(f))(g) == F.map(fa)(f.andThen(g))
  }

  private val all = identityLaw && composition

}