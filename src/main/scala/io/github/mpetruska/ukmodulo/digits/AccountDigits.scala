package io.github.mpetruska.ukmodulo.digits

import io.github.mpetruska.ukmodulo.Error

import scala.language.postfixOps

case class AccountDigits private[ukmodulo] (digits: Vector[Int]) {
  require(digits.length == 14)
  require(digits.forall(0 <=))
  require(digits.forall(9 >=))
}

object AccountDigits {

  val sortCodeLength = 6

  val accountNumberError = "Account number format is not valid"
  val sortCodeError = "Sort code must contain exactly 6 decimal digits without dashes"
  val digitCodeError = "Unknown digit code"

  val digitCodes = "uvwxyzabcdefgh"
  val digitMap: Map[Char, Int] = (digitCodes zip (0 to 14)).toMap

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

  def getSortCode(accountDigits: AccountDigits): String = accountDigits.digits.take(sortCodeLength).mkString

  def getAccountNumber(accountDigits: AccountDigits): String = accountDigits.digits.drop(sortCodeLength).mkString

  def getDigit(accountDigits: AccountDigits, code: Char): Either[Error, Int] = {
    digitMap.get(code)
      .map(accountDigits.digits.apply)
      .toRight(digitCodeError)
  }

  def replaceSortCode(accountDigits: AccountDigits, newSortCode: String): Either[Error, AccountDigits] = {
    parse(newSortCode, getAccountNumber(accountDigits))
  }

}
