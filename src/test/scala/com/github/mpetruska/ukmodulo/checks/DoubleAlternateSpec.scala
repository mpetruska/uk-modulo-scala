package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}
import org.scalatest.{Matchers, WordSpec}

class DoubleAlternateSpec extends WordSpec with Matchers {

  val accountDigits1 = AccountDigits(Vector( 4,  9,  9,  2,  7,  3,  1,  2,  3,  4,  5,  6,  7,  8))
  val weights1 =             Weights(Vector( 2,  1,  2,  1,  2,  1,  2,  1,  2,  1,  2,  1,  2,  1))

  val accountDigits2 = AccountDigits(Vector( 4,  9,  9,  2,  7,  3,  1,  2,  3,  4,  5,  6,  7,  9))
  val weights2 =             Weights(Vector( 2,  1,  2,  1,  2,  1,  2,  1,  2,  1,  2,  1,  2,  1))

  "DoubleAlternate" should {

    "check correctly" in {
      DoubleAlternate.check(accountDigits1, weights1) shouldBe true
      DoubleAlternate.check(accountDigits2, weights2) shouldBe false
    }

  }

}
