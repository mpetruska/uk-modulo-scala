package com.github.mpetruska.ukmodulo.digits.nonstandard

import com.github.mpetruska.ukmodulo.digits.AccountDigits
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class NineDigitSpec extends AnyWordSpec with Matchers {

  val expected = AccountDigits(Vector.fill(5)(0) ++ (1 to 9).toVector)

  "NineDigit" should {

    "parse Santander account numbers correctly" in {
      NineDigit.parseSantanderAccountNumber("000000", "123456789")   shouldBe Right(expected)
      NineDigit.parseSantanderAccountNumber("000000", "12345789ced") shouldBe Left(NineDigit.santanderError)
    }

  }

}
