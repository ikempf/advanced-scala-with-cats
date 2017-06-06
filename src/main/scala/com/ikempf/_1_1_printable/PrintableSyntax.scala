package com.ikempf._1_1_printable

object PrintableSyntax {

  implicit class PrintableOps[A](a: A)(implicit ev: Printable[A]) {
    def format2: String =
      Printable.format(a)

    def print(): Unit =
      Printable.print(a)
  }

}