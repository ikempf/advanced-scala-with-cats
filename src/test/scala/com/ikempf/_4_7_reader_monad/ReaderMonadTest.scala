package com.ikempf._4_7_reader_monad

import com.ikempf._4_7_reader_monad.ReaderMonad.{Db, checkLogin, checkPassword, findUsername}
import org.scalatest.{FlatSpec, Matchers}

class ReaderMonadTest extends FlatSpec with Matchers {

  val db = Db(Map(1 -> "user1", 3 -> "user3"), Map("user3" -> "pass3"))

  "FindUserName" should "find the username" in {
    findUsername(3).run(db) should equal(Some("user3"))
  }

  "CheckPassword" should "check the password" in {
    checkPassword("user3", "pass3").run(db) should be(true)
  }

  "CheckLogin" should "check the login" in {
    checkLogin(3, "pass3").run(db) should be(true)
  }

}