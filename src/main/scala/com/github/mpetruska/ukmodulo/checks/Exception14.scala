package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.{AccountDigits, Weights}

object Exception14 {

  def check(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    EitherChecks.any(
      Right(Standard11.check(accountDigits, weights)),
      secondCheck(accountDigits, weights)
    )
  }

  def secondCheck(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    AccountDigits.getDigit(accountDigits, 'h').map {
      case 0 | 1 | 9 =>
        val shiftedAccountNumber = AccountDigits.shiftAccountNumberRight(accountDigits)
        Standard11.check(shiftedAccountNumber, weights)

      case _ =>
        false
    }
  }

}
