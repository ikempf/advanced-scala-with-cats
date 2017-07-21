package com.ikempf._6_3_cartesian_product

import cats.Monad
import scala.language.higherKinds
import cats.syntax.functor._
import cats.syntax.flatMap._

object Cartesian {

  def product[M[_] : Monad, A, B](fa: M[A], fb: M[B]): M[(A, B)] = {
    fa.flatMap(a => fb.map(b => (a, b)))
  }

}