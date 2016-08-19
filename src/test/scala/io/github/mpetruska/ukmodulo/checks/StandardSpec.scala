package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}
import org.scalatest.{Matchers, WordSpec}

class StandardSpec extends WordSpec with Matchers {

  val accountDigits1 = AccountDigits(Vector( 0,  0,  0,  0,  0,  0,  5,  8,  1,  7,  7,  6,  3,  2))
  val weights1 =             Weights(Vector( 0,  0,  0,  0,  0,  0,  7,  5,  8,  3,  4,  6,  2,  1))

  val accountDigits2 = AccountDigits(Vector( 0,  0,  0,  0,  0,  0,  5,  8,  1,  7,  7,  6,  3,  3))
  val weights2 =             Weights(Vector( 0,  0,  0,  0,  0,  0,  7,  5,  8,  3,  4,  6,  2,  1))

  "Standard11" should {

    "check correctly" in {
      Standard11.check(accountDigits1, weights1) shouldBe true
      Standard11.check(accountDigits2, weights2) shouldBe false
    }

  }

}
