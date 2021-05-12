package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo._
import com.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}

object Exception4 extends Standard11 {

  def performCheck(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    for {
      g <- AccountDigits.getDigit(accountDigits, 'g')
      h <- AccountDigits.getDigit(accountDigits, 'h')
    } yield (calculateSum(accountDigits, weights) % modulus) == (g * 10) + h
  }

}
