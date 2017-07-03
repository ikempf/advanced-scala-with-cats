package com.ikempf._3_6_functor_variance

object CodecInstances {

  val intCodec =
    new Codec[Int] {
      def encode(value: Int): String =
        value.toString
      def decode(value: String): Option[Int] =
        scala.util.Try(value.toInt).toOption
    }

  val boxCodec = intCodec.imap[Box[Int]](Box.apply, _.value)

}