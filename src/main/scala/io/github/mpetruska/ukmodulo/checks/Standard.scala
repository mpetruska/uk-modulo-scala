package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}

trait Standard {

  val modulus: Int

  def calculateSum(accountDigits: AccountDigits, weights: Weights): Int = {
    import Weights._
    sum(dotMul(weights, accountDigits))
  }

  def check(accountDigits: AccountDigits, weights: Weights): Boolean = {
    calculateSum(accountDigits, weights) % modulus == 0
  }

}

trait Standard10 extends Standard {
  val modulus: Int = 10
}

object Standard10 extends Standard10

trait Standard11 extends Standard {
  val modulus: Int = 11
}

object Standard11 extends Standard11
