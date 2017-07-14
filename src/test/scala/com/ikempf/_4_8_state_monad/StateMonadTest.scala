package com.ikempf._4_8_state_monad

import com.ikempf._4_8_state_monad.StateMonad.{evalAll, evalOne}
import org.scalatest.{FlatSpec, Matchers}

class StateMonadTest extends FlatSpec with Matchers {

  "EvalOne" should "evaluate one symbol" in {
    evalOne("1").runS(List(3, 4)).value should equal(List(1, 3, 4))
    evalOne("+").run(List(4, 5, 6)).value should equal(List(9, 6), 9)
    evalOne("-").run(List(4, 5, 6)).value should equal(List(-1, 6), -1)
    evalOne("*").run(List(4, 5, 6)).value should equal(List(20, 6), 20)
    evalOne("/").run(List(20, 5, 6)).value should equal(List(4, 6), 4)
  }

  "EvalAll" should "evaluate all symbols" in {
    evalAll(List("1", "5", "+", "3", "*")).run(Nil).value should equal((List(18), 18))
  }

}