package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}

trait DoubleAlternate {

  val modulus = 10

  def calculateSum(accountDigits: AccountDigits, weights: Weights): Int = {
    import Weights._
    sumDigits(dotMul(weights, accountDigits))
  }

  def check(accountDigits: AccountDigits, weights: Weights): Boolean = {
    calculateSum(accountDigits, weights) % modulus == 0
  }

}

object DoubleAlternate extends DoubleAlternate
