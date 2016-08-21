package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo._
import io.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}

object Exception3 extends DoubleAlternate {

  def performCheck(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    AccountDigits.getDigit(accountDigits, 'c').right
      .map {
        case 6 | 9 => true
        case _     => super.check(accountDigits, weights)
      }
  }

}
