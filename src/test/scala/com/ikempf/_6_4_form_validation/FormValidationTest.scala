package com.ikempf._6_4_form_validation

import cats.data.Validated.{Invalid, Valid}
import com.ikempf._6_4_form_validation.FormValidation.{readAge, readName, readUser}
import org.scalatest.{FlatSpec, Matchers}

class FormValidationTest extends FlatSpec with Matchers {

  "readName" should "extract valid name" in {
    // Given
    val validName = "name"
    val fields = Map("name" -> validName)

    // Then
    val res = readName(fields)

    // Then
    res should equal(Right(validName))
  }

  it should "fail when name is missing" in {
    // Given
    val fields = Map("not name" -> "name")

    // Then
    val res = readName(fields)

    // Then
    res should equal(Left(List("name is missing")))
  }

  it should "fail for invalid name" in {
    // Given
    val invalidName = ""
    val fields = Map("name" -> invalidName)

    // Then
    val res = readName(fields)

    // Then
    res should equal(Left(List("name can not be empty")))
  }

  "readAge" should "extract valid age" in {
    // Given
    val validAge = 25
    val fields = Map("age" -> validAge.toString)

    // Then
    val res = readAge(fields)

    // Then
    res should equal(Right(validAge))
  }

  it should "fail when age is missing" in {
    // Given
    val fields = Map("not age" -> "25")

    // Then
    val res = readAge(fields)

    // Then
    res should equal(Left(List("age is missing")))
  }

  it should "fail for invalid age" in {
    // Given
    val fields = Map("age" -> "str")

    // Then
    val res = readAge(fields)

    // Then
    res should equal(Left(List("age must be an integer")))
  }

  it should "fail for negative age" in {
    // Given
    val fields = Map("age" -> "-15")

    // Then
    val res = readAge(fields)

    // Then
    res should equal(Left(List("age must be a positive integer")))
  }

  "readUser" should "read user" in {
    // Given
    val fields = Map("name" -> "username", "age" -> "25")

    // Then
    val res = readUser(fields)

    // Then
    res should equal(Valid(User("username", 25)))
  }

  it should "fail on invalid user" in {
    // Given
    val fields = Map("name" -> "", "age" -> "-25")

    // Then
    val res = readUser(fields)

    // Then
    res should equal(Invalid(List("name can not be empty", "age must be a positive integer")))
  }

}