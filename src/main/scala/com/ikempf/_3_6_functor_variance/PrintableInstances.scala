package com.ikempf._3_6_functor_variance

object PrintableInstances {

  implicit val stringPrintable =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

}