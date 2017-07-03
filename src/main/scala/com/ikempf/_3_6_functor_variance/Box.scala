package com.ikempf._3_6_functor_variance

case class Box[A](value: A)

object Box {

  implicit def printableInstance[A](implicit printable: Printable[A]) =
    printable.contramap[Box[A]](_.value)

}