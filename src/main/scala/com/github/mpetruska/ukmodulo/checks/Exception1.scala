package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}

object Exception1 extends DoubleAlternate {

  override def calculateSum(accountDigits: AccountDigits, weights: Weights): Int = {
    super.calculateSum(accountDigits, weights) + 27
  }

}
