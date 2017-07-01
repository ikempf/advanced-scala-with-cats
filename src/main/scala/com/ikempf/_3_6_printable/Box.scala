package com.ikempf._3_6_printable

case class Box[A](value: A)

object Box {

  def printableInstance[A](implicit printable: Printable[A]) =
    printable.contramap[Box[A]](_.value)

}