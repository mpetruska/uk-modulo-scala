package io.github.mpetruska.ukmodulo.nonstandard

import io.github.mpetruska.ukmodulo.digits.AccountDigits

object SevenDigit {

  type Error = String

  def error = "Seven digit account number must be in format 1234567"

  def parse(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val accountNumberFormat = "[0-9]{7}".r

    accountNumber match {
      case accountNumberFormat() => AccountDigits.parse(sortCode, s"0$accountNumber")
      case _                     => Left(error)
    }
  }

}
