package com.github.mpetruska.ukmodulo.digits.nonstandard

import com.github.mpetruska.ukmodulo.digits.AccountDigits
import org.scalatest.{Matchers, WordSpec}

class SevenDigitSpec extends WordSpec with Matchers {

  val expected = AccountDigits(Vector.fill(7)(0) ++ (1 to 7).toVector)

  "SevenDigit" should {

    "parse account numbers correctly" in {
      SevenDigit.parse("000000", "1234567") shouldBe Right(expected)
      SevenDigit.parse("000000", "123457") shouldBe Left(SevenDigit.error)
    }

  }

}
