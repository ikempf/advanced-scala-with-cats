package com.ikempf._1_1_printable

object PrintableSyntax {

  implicit class PrintOps[A](a: A)(implicit ev: Printable[A]) {
    def format(): String =
      Printable.format(a)

    def print(): Unit =
      Printable.print(a)
  }

}