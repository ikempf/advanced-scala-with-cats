package com.ikempf._11_2_gcounter

import com.ikempf._2_3_bool_monoids.Monoid

final case class GCounterA[A](counters: Map[String, A]) {

  def increment(machine: String, amount: A)(implicit m: Monoid[A]): GCounterA[A] = {
    val newAmount = counters.get(machine).map(m.combine(amount, _)).getOrElse(amount)

    GCounterA(counters + (machine -> newAmount))
  }

  def get(implicit m: Monoid[A]): A =
    counters.values.foldRight(m.empty)(m.combine)

  def merge(that: GCounterA[A])(implicit bl: BoundedSemiLattice[A]): GCounterA[A] = {
    val machineAmounts = counters.toList ++ that.counters.toList

    val newCounters = machineAmounts
      .groupBy {
        case (machine, _) => machine
      }
      .map {
        case (machine, amounts) =>
          val totalAmount = amounts.map(_._2).foldRight(bl.empty)(bl.combine)
          machine -> totalAmount
      }

    GCounterA(newCounters)
  }

}
