package com.ikempf.si_2712_partial_unification

import cats.syntax.traverse._
import cats.instances.either._
import cats.instances.option._
import cats.instances.list._

object Partial {
  val eithers: List[Either[Int, String]] = List(
    Right("Wow!"),
    Right("Such cool!")
  )
  val options: List[Option[String]] = List(
    Some("Wow!"),
    Some("Such cool!")
  )

  eithers.sequence
  options.sequence

}