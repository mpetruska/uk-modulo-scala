package io.github.mpetruska.ukmodulo.digits

import scala.language.postfixOps

case class AccountDigits(digits: Vector[Int]) {
  require(digits.length == 14)
  require(digits.forall(0 <=))
  require(digits.forall(9 >=))
}

object AccountDigits {

  type Error = String

  val accountNumberError = "Account number must contain exactly 8 decimal digits"
  val sortCodeError = "Sort code must contain exactly 6 decimal digits"

  def parse(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val sortCodeFormat = "[0-9]{6}".r
    val accountNumberFormat = "[0-9]{8}".r

    (sortCode, accountNumber) match {
      case (sortCodeFormat(), accountNumberFormat()) => Right(AccountDigits(s"$sortCode$accountNumber".toVector.map(_.asDigit)))
      case (sortCodeFormat(), _)                     => Left(accountNumberError)
      case (_, accountNumberFormat())                => Left(sortCodeError)
      case _                                         => Left(s"$sortCodeError, $accountNumberError")
    }
  }

}
