package com.github.mpetruska.ukmodulo.checks

object EitherChecks {

  def all[E](eithers: Either[E, Boolean]*): Either[E, Boolean] = {
    eithers.reduce[Either[E, Boolean]] {
      case (left @ Left(_), _)                => left
      case (_, left @ Left(_))                => left
      case (right @ Right(true), Right(true)) => right
      case (right @ Right(false), _)          => right
      case (_, right @ Right(false))          => right
    }
  }

  def any[E](either1: Either[E, Boolean], either2: => Either[E, Boolean]): Either[E, Boolean] = {
    either1 match {
      case right @ Right(true)    => right
      case Left(_) | Right(false) => either2
    }
  }

}
