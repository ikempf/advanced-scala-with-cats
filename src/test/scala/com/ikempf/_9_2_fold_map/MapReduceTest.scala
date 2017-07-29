package com.ikempf._9_2_fold_map

import cats.instances.string._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}

class MapReduceTest extends FlatSpec with Matchers with ScalaFutures {

  "foldMap" should "map and fold" in {
    // Given
    val ints = Vector(1, 2, 3, 4)

    // When
    val res = MapReduce.foldMap(ints)(_.toString)

    // Then
    res should equal("1234")
  }

  "parallelFoldMap" should "map and fold" in {
    // Given
    val ints = Vector(1, 2, 3, 4)

    // When
    val res = MapReduce.parallelFoldMap(ints)(_.toString)

    // Then
    res.futureValue should equal("1234")
  }

  "parallelFoldMapCats" should "map and fold" in {
    // Given
    val ints = Vector(1, 2, 3, 4)

    // When
    val res = MapReduce.parallelFoldMapCats(ints)(_.toString)

    // Then
    res.futureValue should equal("1234")
  }

}