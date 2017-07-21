package com.ikempf._6_4_form_validation

import cats.data.Validated
import cats.instances.list._
import cats.syntax.either._
import cats.syntax.cartesian._

import scala.util.Try

object FormValidation {

  val Name = "name"
  val Age = "age"


  def readUser(fields: Map[String, String]): Validated[List[String], User] =
    (catsSyntaxUCartesian(readName(fields).toValidated) |@| readAge(fields).toValidated)
      .map(User.apply)

  def readName(fields: Map[String, String]): Either[List[String], String] =
    readField(fields, Name)
      .flatMap(nonBlank(Name))

  def readAge(fields: Map[String, String]): Either[List[String], Int] =
    readField(fields, Age)
      .flatMap(parseInt(Age))
      .flatMap(nonNegative(Age))

  private def readField(fields: Map[String, String], name: String) =
    fields
      .get(name)
      .toRight(List(s"$name is missing"))

  private def nonBlank(name: String)(data: String) =
    Option(data)
      .filterNot(_.isEmpty)
      .toRight(List(s"$name can not be empty"))

  private def parseInt(name: String)(data: String) =
    Try(data.toInt)
      .toOption
      .toRight(List(s"$name must be an integer"))

  private def nonNegative(name: String)(data: Int) =
    Option(data)
      .filter(_ >= 0)
      .toRight(List(s"$name must be a positive integer"))

}