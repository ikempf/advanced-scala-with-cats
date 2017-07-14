package com.ikempf._4_9_custom_monads

import cats.Monad
import com.ikempf._3_5_functors.Tree
import com.ikempf._3_5_functors.Tree.{Branch, Leaf}
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.annotation.tailrec
import cats.syntax.either._

object TreeMonad {

  val treeMonad = new Monad[Tree] {
    override def pure[A](x: A): Tree[A] =
      Leaf(x)

    override def flatMap[A, B](fa: Tree[A])(f: (A) => Tree[B]): Tree[B] =
      fa match {
        case Leaf(a) => f(a)
        case Branch(l, r) => Branch(flatMap(l)(f), flatMap(r)(f))
      }

    // todo: This is not tail recursive
    override def tailRecM[A, B](a: A)(f: (A) => Tree[Either[A, B]]): Tree[B] = {
      f(a) match {
        case Leaf(Left(b)) => tailRecM(b)(f)
        case Leaf(Right(b)) => pure(b)
        case Branch(l, r) =>
          Branch(
            flatMap(l) {
              case Left(b) => tailRecM(b)(f)
              case Right(b) => pure(b)
            },
            flatMap(r) {
              case Left(b) => tailRecM(b)(f)
              case Right(b) => pure(b)
            }
          )
      }
    }
  }

}


object Foo {

  private val leaf: Tree[Int] = Tree.Leaf(5)
  private val branch: Tree[Int] = Branch(Leaf(5), Leaf(3))

  implicit val t = TreeMonad.treeMonad
  for {
    b <- branch
    l <- leaf
  } yield l

}