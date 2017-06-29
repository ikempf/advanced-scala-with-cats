package com.ikempf._2_5_adding_all_the_things

import cats.Monoid

object SuperAdder {

  def add[A: Monoid](items: List[A]): A =
    items.fold(Monoid[A].empty)(Monoid[A].combine)

}