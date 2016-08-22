package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo._
import com.github.mpetruska.ukmodulo.digits.{AccountDigits, Weights}

object Exceptions2And9 extends Standard11 {

  val nonNineSubstitute   = Weights(Vector(0, 0, 1, 2, 5, 3, 6, 4, 8, 7, 10, 9, 3, 1))
  val nineSubstitute      = Weights(Vector(0, 0, 0, 0, 0, 0, 0, 0, 8, 7, 10, 9, 3, 1))
  val replacementSortCode = "309634"

  def check(accountDigits: AccountDigits, weight2: Weights, weight9: Weights): Either[Error, Boolean] = {
    EitherChecks.any(
      exception2Check(accountDigits, weight2),
      exception9Check(accountDigits, weight9)
    )
  }

  def exception2Check(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    import AccountDigits._
    for {
      a <- getDigit(accountDigits, 'a').right
      g <- getDigit(accountDigits, 'g').right
    } yield {
      (a, g) match {
        case (0, _) => super.check(accountDigits, weights)
        case (_, 9) => super.check(accountDigits, nineSubstitute)
        case (_, _) => super.check(accountDigits, nonNineSubstitute)
      }
    }
  }

  def exception9Check(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    AccountDigits.replaceSortCode(accountDigits, replacementSortCode).right
      .map(replaced => super.check(replaced, weights))
  }

}
