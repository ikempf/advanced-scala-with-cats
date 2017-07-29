package com.ikempf._4_8_state_monad

import cats.data.State

import scala.util.Try

object StateMonad {

  type CalcState[A] = State[List[Int], A]

  def evalOne(token: String): CalcState[Int] = {
    State[List[Int], Int] {
      state =>
        token match {
          case "+" =>
            newState(state, _ + _)
          case "-" =>
            newState(state, _ - _)
          case "*" =>
            newState(state, _ * _)
          case "/" =>
            newState(state, _ / _)
          case num => (num.toInt +: state, num.toInt)
        }
    }
  }

  private def newState(state: List[Int], op: (Int, Int) => Int) = {
    val value = op(state.head, state.tail.head)
    (value +: state.drop(2), value)
  }

  def evalAll(input: List[String]): CalcState[Int] = {
    input match {
      case Nil => State.pure(0)
      case h :: Nil => evalOne(h)
      case h :: t => evalOne(h).flatMap(_ => evalAll(t))
    }
  }

}