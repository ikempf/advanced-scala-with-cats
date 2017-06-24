package com.ikempf._1_1_printable

object PrintableInstances {

  implicit val stringPrintable = new Printable[String] {
    override def format(a: String): String = a
  }

  implicit val intPrintable = new Printable[Int] {
    override def format(a: Int): String = a.toString
  }

  implicit val catsPrintable = new Printable[Cat] {
    override def format(cat: Cat): String =
      s"${cat.name.toUpperCase} is a ${cat.age} year-old ${cat.color.toUpperCase} cat."
  }

}