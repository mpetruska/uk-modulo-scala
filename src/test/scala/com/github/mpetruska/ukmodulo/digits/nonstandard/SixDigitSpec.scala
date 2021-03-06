package com.github.mpetruska.ukmodulo.digits.nonstandard

import com.github.mpetruska.ukmodulo.digits.AccountDigits
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class SixDigitSpec extends AnyWordSpec with Matchers {

  val expected = AccountDigits(Vector.fill(8)(0) ++ (1 to 6).toVector)

  "SixDigit" should {

    "parse account numbers correctly" in {
      SixDigit.parse("000000", "123456")  shouldBe Right(expected)
      SixDigit.parse("000000", "1234507") shouldBe Left(SixDigit.error)
    }

  }

}
