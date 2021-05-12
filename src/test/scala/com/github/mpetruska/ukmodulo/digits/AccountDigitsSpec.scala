package com.github.mpetruska.ukmodulo.digits

import org.scalacheck.Gen
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class AccountDigitsSpec extends AnyWordSpec with ScalaCheckPropertyChecks with Matchers {

  def digitsGenerator(n: Int): Gen[Array[Int]] =
    Gen.listOfN(n, Gen.chooseNum(0, 9)).map(_.toArray).suchThat(_.length == n)

  "AccountDigits" should {

    "parse correctly" in {
      forAll(digitsGenerator(6), digitsGenerator(8)) { (sortCodeDigits, accountNumberDigits) =>
        val sortCode      = sortCodeDigits.mkString
        val accountNumber = accountNumberDigits.mkString
        val result        = AccountDigits.parse(sortCode, accountNumber)
        
        result.isRight          shouldBe true
        result.right.get.digits shouldBe Array.concat(sortCodeDigits, accountNumberDigits)
      }
    }

    "report parsing failure correctly" in {
      AccountDigits.parse("12", "12345678")     shouldBe Left(AccountDigits.sortCodeError)
      AccountDigits.parse("abcdef", "12345678") shouldBe Left(AccountDigits.sortCodeError)
      AccountDigits.parse("123456", "123458")   shouldBe Left(AccountDigits.accountNumberError)
      AccountDigits.parse("123456", "abcdefgh") shouldBe Left(AccountDigits.accountNumberError)
      AccountDigits.parse("", "")               shouldBe Left(AccountDigits.sortCodeError + ", " + AccountDigits.accountNumberError)
    }

  }

}
