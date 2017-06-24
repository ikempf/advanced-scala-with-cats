package com.ikempf._1_2_show

import cats.Show
import com.ikempf._1_1_printable.Cat
import org.scalatest.{FlatSpec, Matchers}
import ShowInstances.catsShow


class ShowInstancesTest extends FlatSpec with Matchers {

  "CatsShow" should "show cat" in {
    val cat = Cat("joe", 3, "blue")
    Show[Cat].show(cat) should equal("JOE is a 3 year-old BLUE cat.")
  }

}