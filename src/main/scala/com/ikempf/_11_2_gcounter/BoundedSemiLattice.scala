package com.ikempf._11_2_gcounter

import com.ikempf._2_3_bool_monoids.Monoid

trait BoundedSemiLattice[A] extends Monoid[A]

object BoundedSemiLattice {

  implicit val intInstance: BoundedSemiLattice[Int] = new BoundedSemiLattice[Int] {
    override def empty: Int = 0

    override def combine(x: Int, y: Int): Int = x max y
  }

  implicit def setInstance[A](): BoundedSemiLattice[Set[A]] = new BoundedSemiLattice[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
  }

}

object Monoid {
  implicit val intInstance: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0

    override def combine(x: Int, y: Int): Int = x + y
  }
}