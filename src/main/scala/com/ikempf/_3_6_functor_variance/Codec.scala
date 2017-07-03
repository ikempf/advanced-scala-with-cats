package com.ikempf._3_6_functor_variance

trait Codec[A] {
  def encode(value: A): String

  def decode(value: String): Option[A]

  def imap[B](dec: A => B, enc: B => A): Codec[B] =
    new Codec[B] {
      override def encode(value: B): String = Codec.this.encode(enc(value))

      override def decode(value: String): Option[B] = Codec.this.decode(value).map(dec)
    }
}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
    c.decode(value)
}