package com.ikempf._1_3_eq

import cats.kernel.Eq
import com.ikempf._1_1_printable.Cat

object EqInstances {

  implicit val catEq: Eq[Cat] = Eq.instance[Cat](_ == _)

}