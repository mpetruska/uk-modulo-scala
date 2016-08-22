package com.github.mpetruska.ukmodulo.digits.nonstandard

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.AccountDigits

object SevenDigit {

  def error = "Seven digit account number must be in format 1234567"

  def parse(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val accountNumberFormat = "[0-9]{7}".r

    accountNumber match {
      case accountNumberFormat() => AccountDigits.parse(sortCode, s"0$accountNumber")
      case _                     => Left(error)
    }
  }

}
