package com.ikempf._11_2_gcounter

import com.ikempf._2_3_bool_monoids.Monoid

final case class GCounter(counters: Map[String, Int]) {

  def increment(machine: String, amount: Int): GCounter = {
    val newAmount = counters.get(machine).map(_ + amount).getOrElse(amount)

    GCounter.apply(counters + (machine -> newAmount))
  }

  def get: Int =
    counters.values.sum

  def merge(that: GCounter): GCounter = {
    val machineAmounts = counters.toList ++ that.counters.toList

    val newCounters = machineAmounts
      .groupBy {
        case (machine, _) => machine
      }
      .map {
        case (machine, amounts) =>
          val totalAmount = amounts.map(_._2).max
          machine -> totalAmount
      }

    GCounter(newCounters)
  }

}

