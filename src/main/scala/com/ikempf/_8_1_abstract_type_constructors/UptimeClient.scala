package com.ikempf._8_1_abstract_type_constructors

import cats.{Applicative, Id}

import scala.concurrent.Future
import scala.language.higherKinds
import cats.syntax.traverse._
import cats.instances.list._
import cats.syntax.functor._

trait UptimeClient[F[_]] {
  def getUptime(hostname: String): F[Int]
}

class TestUptimeClient(hosts: Map[String, Int]) extends UptimeClient[Id] {
  override def getUptime(hostname: String): Int = hosts(hostname)
}

object RealUptimeClient extends UptimeClient[Future] {
  override def getUptime(hostname: String): Future[Int] = ???
}

class UptimeService[F[_]: Applicative](client: UptimeClient[F]) {
  def getTotalUptime(hostnames: List[String]): F[Int] =
    hostnames.traverse(client.getUptime).map(_.sum)
}