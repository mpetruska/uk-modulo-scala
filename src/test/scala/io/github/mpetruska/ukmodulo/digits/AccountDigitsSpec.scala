package io.github.mpetruska.ukmodulo.digits

import org.scalacheck.Gen
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.PropertyChecks

class AccountDigitsSpec extends WordSpec with PropertyChecks with Matchers {

  def digitsGenerator(n: Int): Gen[Array[Int]] =
    Gen.listOfN(n, Gen.chooseNum(0, 9)).map(_.toArray).suchThat(_.length == n)

  "AccountDigits" should {

    "parse correctly" in {
      forAll(digitsGenerator(6), digitsGenerator(8)) { (sortCodeDigits, accountNumberDigits) =>
        val sortCode = sortCodeDigits.mkString
        val accountNumber = accountNumberDigits.mkString
        val result = AccountDigits.parse(sortCode, accountNumber)
        result.isRight shouldBe true
        result.right.get.digits shouldBe Array.concat(sortCodeDigits, accountNumberDigits)
      }
    }

  }

}
