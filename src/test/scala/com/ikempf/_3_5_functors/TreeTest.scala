package com.ikempf._3_5_functors

import com.ikempf._3_5_functors.Tree.{Branch, Leaf}
import org.scalacheck.Gen
import org.scalatest.prop.Checkers
import org.scalatest.{FlatSpec, Matchers}


class TreeTest extends FlatSpec with Matchers with Checkers {

  val fGen: Gen[(Int) => String] = Gen.choose(0, 100).map(i => (j: Int) => (i * j).toString)

  val gGen: Gen[(String) => List[Char]] = Gen.alphaNumStr.map(s => (s2: String) => (s + s2).toList)

  val treeGen: Gen[Tree[Int]] = Gen.oneOf(true, false).flatMap { b =>
    if (b)
      Gen.choose(Int.MinValue, Int.MaxValue).map(Leaf(_))
    else
      for {
        l <- treeGen
        r <- treeGen
      } yield Branch(l, r)
  }

  "TreeFunctor" should "satisfy functor laws" in {
    check(FunctorLaws(fGen, gGen, treeGen)(Tree.treeFunctor))
  }

}