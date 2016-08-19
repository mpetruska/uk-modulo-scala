package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}

trait Standard {

  val modulus: Int

  def check(accountDigits: AccountDigits, weights: Weights): Boolean = {
    import Weights._
    sum(dotMul(weights, accountDigits)) % modulus == 0
  }

}

object Standard10 extends Standard {
  val modulus: Int = 10
}

object Standard11 extends Standard {
  val modulus: Int = 11
}
