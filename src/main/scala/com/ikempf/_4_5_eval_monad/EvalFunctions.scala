package com.ikempf._4_5_eval_monad

import cats.Eval

object EvalFunctions {

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] =
    as match {
      case head :: tail =>
        foldRight(tail, acc)(fn).map(fn(head, _))
      case Nil =>
        Eval.now(acc)
    }

}