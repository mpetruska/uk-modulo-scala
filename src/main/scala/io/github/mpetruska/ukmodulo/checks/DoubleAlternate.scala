package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}

object DoubleAlternate {

  val modulus = 10

  def check(accountDigits: AccountDigits, weights: Weights): Boolean = {
    import Weights._
    sumDigits(dotMul(weights, accountDigits)) % modulus == 0
  }

}
