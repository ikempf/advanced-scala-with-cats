package com.ikempf._1_1_printable

import com.ikempf._1_1_printable.PrintableInstances._
import com.ikempf._1_1_printable.PrintableSyntax._
import org.scalatest.{FlatSpec, Matchers}

class PrintableSyntaxTest extends FlatSpec with Matchers {

  "PrintableOps" should "format string" in {
    "test".format2 should equal("test")
  }

  it should "format int" in {
    5.format2 should equal("5")
  }

}
