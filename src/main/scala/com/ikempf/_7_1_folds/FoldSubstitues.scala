package com.ikempf._7_1_folds

import cats.{Foldable, Traverse}

import scala.concurrent.Future

object FoldSubstitues {

  def foldMap[A, B](l: List[A])(f: A => B): List[B] =
    l.foldRight(List.empty[B])((a, z) => f(a) +: z)

  def foldFlatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
    l.foldRight(List.empty[B])((a, z) => f(a) ++ z)

  def foldFilter[A](l: List[A])(f: A => Boolean): List[A] =
    l.foldRight(List.empty[A]) { (a, z) =>
      if (f(a))
        a +: z
      else
        z
    }

  def foldSum(l: List[Int]): Int =
    l.foldRight(0)(_ + _)

}