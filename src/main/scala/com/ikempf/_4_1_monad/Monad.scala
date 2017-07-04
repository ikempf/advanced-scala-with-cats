package com.ikempf._4_1_monad

import cats.instances.function._
import cats.syntax.functor._

import scala.language.higherKinds

trait Monad[F[_]] {

  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(f: A => B): F[B] = flatMap(value)(f.map(pure))

}