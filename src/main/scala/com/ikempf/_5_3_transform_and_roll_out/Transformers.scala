package com.ikempf._5_3_transform_and_roll_out

import cats.data.EitherT
import cats.syntax.either._
import cats.instances.future._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Transformers {

  type Response[A] = Future[Either[String, A]]
  type ResponseT[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(autobot: String): ResponseT[Int] = {
    powerLevels
      .get(autobot)
      .map(_.asRight[String])
      .map(powerLevel => EitherT(Future.successful(powerLevel)))
      .getOrElse(EitherT(Future.successful(s"$autobot is no reachable".asLeft)))
  }

  def canSpecialMove(ally1: String, ally2: String): ResponseT[Boolean] =
    for {
      power1 <- getPowerLevel(ally1)
      power2 <- getPowerLevel(ally2)
    } yield power1 + power2 > 15

  def tacticalReport(ally1: String, ally2: String): String =
    Await.result(canSpecialMove(ally1, ally2).value, 5.seconds) match {
      case Left(msg) =>
        s"Comms error: $msg"
      case Right(true) =>
        s"$ally1 and $ally2 are ready to roll out!"
      case Right(false) =>
        s"$ally1 and $ally2 need a recharge."
    }

}