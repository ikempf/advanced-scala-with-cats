package com.ikempf._1_1_printable

trait Printable[A] {
  def format(a: A): String
}

object Printable {

  def format[A](a: A)(implicit ev: Printable[A]): String =
    ev.format(a)

  def print[A](a: A)(implicit ev: Printable[A]): Unit =
    println(format(a))

}