package com.ikempf._3_6_functor_variance

import org.scalatest.{FlatSpec, Matchers}
import PrintableInstances._
import Box.printableInstance

class BoxTest extends FlatSpec with Matchers {

  "BoxPrintable" should "format box" in {
    Printable.format(Box("str")) should equal(""""str"""")
    Printable.format(Box(true)) should equal("yes")
  }

}
