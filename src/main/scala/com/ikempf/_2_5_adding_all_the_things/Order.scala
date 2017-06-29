package com.ikempf._2_5_adding_all_the_things

import cats.kernel.Monoid

object Order {

  implicit val orderMonoid =
    new Monoid[Order] {
      override def empty: Order = Order(0, 0)
      override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    }

}

case class Order(totalCost: Double, quantity: Double)