package com.ikempf._9_2_fold_map

import cats.Monoid
import cats.syntax.monoid._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.syntax.traverse._
import cats.instances.vector._
import cats.instances.future._
import cats.syntax.foldable._

object MapReduce {

  def foldMap[A, B: Monoid](as: Vector[A])(f: A => B): B =
    as.foldRight(Monoid[B].empty)((a, z) => f(a) |+| z)

  def parallelFoldMap[A, B: Monoid](as: Vector[A])(f: A => B): Future[B] = {
    val processors = Runtime.getRuntime.availableProcessors()
    val groupSize = as.length / processors + as.length % processors
    val groups = as.grouped(groupSize)
    val eventualFolds = groups.map(group => Future(foldMap(group)(f)))

    eventualFolds
      .toVector
      .sequenceU
      .map(folds => folds.combineAll)
  }

  def parallelFoldMapCats[A, B: Monoid](as: Vector[A])(f: A => B): Future[B] = {
    val processors = Runtime.getRuntime.availableProcessors()
    val groupSize = as.length / processors + as.length % processors

    as
      .grouped(groupSize)
      .toVector
      .traverse(group => Future(group.foldMap(f)))
      .map(_.combineAll)
  }

}