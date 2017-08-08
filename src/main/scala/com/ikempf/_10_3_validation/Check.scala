package com.ikempf._10_3_validation

import cats.kernel.Monoid
import cats.syntax.monoid._
import com.ikempf._10_3_validation.Check.{And, Or}

sealed trait Check[E, A] {

  def verify(a: A): Either[E, A]

  def and(c: Check[E, A])(implicit ev: Monoid[E]): Check[E, A] =
    And(this, c)

  def or(c: Check[E, A])(implicit ev: Monoid[E]): Check[E, A] =
    Or(this, c)

}

object Check {

  case class Pure[E: Monoid, A](e: E)(f: A => Boolean) extends Check[E, A] {
    override def verify(a: A): Either[E, A] =
      if (f(a))
        Right(a)
      else
        Left(e)

  }

  case class And[E: Monoid, A](l: Check[E, A], r: Check[E, A]) extends Check[E, A] {
    override def verify(a: A): Either[E, A] =
      (l.verify(a), r.verify(a)) match {
        case (Left(le), Left(re)) => Left(le |+| re)
        case (Left(le), Right(_)) => Left(le)
        case (Right(_), Left(re)) => Left(re)
        case (Right(_), Right(_)) => Right(a)
      }
  }

  case class Or[E: Monoid, A](l: Check[E, A], r: Check[E, A]) extends Check[E, A] {
    override def verify(a: A): Either[E, A] =
      (l.verify(a), r.verify(a)) match {
        case (Left(le), Left(re)) => Left(le |+| re)
        case (Left(_), Right(_)) => Right(a)
        case (Right(_), Left(_)) => Right(a)
        case (Right(_), Right(_)) => Right(a)
      }
  }

  def apply[E: Monoid, A](e: E, f: A => Boolean): Check[E, A] =
    Pure(e)(f)

  cats.data.Reader((i: Int) => i.toString)
}