package com.ikempf._7_1_folds

object ReflectingFolds {

  def foldLeft[A](l: List[A]): List[A] =
    l.foldLeft(List.empty[A])(_ :+ _)

  def foldRight[A](l: List[A]): List[A] =
    l.foldRight(List.empty[A])(_ +: _)

}