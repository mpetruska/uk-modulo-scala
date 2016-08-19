package io.github.mpetruska.ukmodulo.nonstandard

import io.github.mpetruska.ukmodulo.digits.AccountDigits

object SixDigit {

  type Error = String

  def error = "Six digit account number must be in format 123456"

  def parse(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val accountNumberFormat = "[0-9]{6}".r

    accountNumber match {
      case accountNumberFormat() => AccountDigits.parse(sortCode, s"00$accountNumber")
      case _                     => Left(error)
    }
  }

}
