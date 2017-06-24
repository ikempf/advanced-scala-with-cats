package com.ikempf._1_1_printable

import org.scalatest.{FlatSpec, Matchers}
import PrintableInstances._

class PrintableInstancesTest extends FlatSpec with Matchers {

  "PrintableInt" should "format ints" in {
    Printable.format(5) should equal("5")
  }

  "PrintableString" should "format strings" in {
    Printable.format("str") should equal("str")
  }

  "PrintableCat" should "format cats" in {
    val cat = Cat("bob", 5, "brown")
    Printable.format(cat) should equal("BOB is a 5 year-old BROWN cat.")
  }

}