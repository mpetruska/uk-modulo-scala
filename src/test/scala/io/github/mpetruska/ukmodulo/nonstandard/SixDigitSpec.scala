package io.github.mpetruska.ukmodulo.nonstandard

import io.github.mpetruska.ukmodulo.digits.AccountDigits
import org.scalatest.{Matchers, WordSpec}

class SixDigitSpec extends WordSpec with Matchers {

  val expected = AccountDigits(Vector.fill(8)(0) ++ (1 to 6).toVector)

  "SixDigit" should {

    "parse account numbers correctly" in {
      SixDigit.parse("000000", "123456") shouldBe Right(expected)
      SixDigit.parse("000000", "1234507") shouldBe Left(SixDigit.error)
    }

  }

}
