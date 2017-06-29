package com.ikempf._2_4_set_monoids

import com.ikempf._2_3_bool_monoids.{Monoid, Semigroup}

object SetMonoids {

  def unionMonoid[A] =
    new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty
      override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
    }

  def intersectionSemigroup[A] =
    new Semigroup[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] =
        a intersect b
    }

}