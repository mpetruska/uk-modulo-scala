package com.github.mpetruska.ukmodulo.digits.nonstandard

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.AccountDigits

object SixDigit {

  def error = "Six digit account number must be in format 123456"

  def parse(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val accountNumberFormat = "[0-9]{6}".r

    accountNumber match {
      case accountNumberFormat() => AccountDigits.parse(sortCode, s"00$accountNumber")
      case _                     => Left(error)
    }
  }

}
