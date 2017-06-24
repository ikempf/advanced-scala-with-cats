package com.ikempf._1_2_show

import cats.Show
import com.ikempf._1_1_printable.Cat

object ShowInstances {

  implicit val catsShow = Show.show[Cat](cat => s"${cat.name.toUpperCase} is a ${cat.age} year-old ${cat.color.toUpperCase} cat.")

}