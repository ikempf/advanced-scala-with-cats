package com.ikempf._3_6_functor_variance

import org.scalatest.{FlatSpec, Matchers}

class CodecInstancesTest extends FlatSpec with Matchers {

  "BoxCodec" should "encode string" in {
    CodecInstances.boxCodec.encode(Box(123)) should equal("123")
    CodecInstances.boxCodec.decode("123").get should equal(Box(123))
  }

}