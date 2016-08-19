package io.github.mpetruska.ukmodulo.digits

import org.scalatest.{Matchers, WordSpec}

class WeightsSpec extends WordSpec with Matchers {

  val accountDigits1 = AccountDigits(Vector( 4,  9,  9,  2,  7,  3,  1,  2,  3,  4,  5,  6,  7,  8))
  val weights1 =             Weights(Vector( 2,  1,  2,  1,  2,  1,  2,  1,  2,  1,  2,  1,  2,  1))
  val result1 =              Weights(Vector( 8,  9, 18,  2, 14,  3,  2,  2,  6,  4, 10,  6, 14,  8))

  val accountDigits2 = AccountDigits(Vector( 0,  0,  0,  0,  0,  0,  5,  8,  1,  7,  7,  6,  3,  2))
  val weights2 =             Weights(Vector( 0,  0,  0,  0,  0,  0,  7,  5,  8,  3,  4,  6,  2,  1))
  val result2 =              Weights(Vector( 0,  0,  0,  0,  0,  0, 35, 40,  8, 21, 28, 36,  6,  2))

  "Weights" should {

    "be dot-multiplied correctly" in {
      Weights.dotMul(weights1, accountDigits1) shouldBe result1
      Weights.dotMul(weights2, accountDigits2) shouldBe result2
    }

    "sum its digits correctly" in {
      Weights.sumDigits(result1) shouldBe 70
    }

    "sum correctly" in {
      Weights.sum(result2) shouldBe 176
    }

  }

}
