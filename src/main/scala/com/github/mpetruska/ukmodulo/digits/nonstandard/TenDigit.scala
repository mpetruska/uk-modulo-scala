package com.github.mpetruska.ukmodulo.digits.nonstandard

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.AccountDigits

object TenDigit {

  val nationalWestMinsterError                = "National Westminster account numbers must be in format 0123456789 or 01-23456789"
  val coOperativeAndLeedsBuildingSocietyError = "Co-Operative Bank and Leeds Building Society account numbers must be in format 0123456789"

  def parseNationalWestminsterAccountNumber(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val accountNumberParser = "[0-9]{2}[-]?([0-9]{8})".r

    accountNumber match {
      case accountNumberParser(lastEight) => AccountDigits.parse(sortCode, lastEight)
      case _                              => Left(nationalWestMinsterError)
    }
  }

  def parseCoOperativeOrLeedsBuildingSocietyAccountNumber(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val accountNumberParser = "([0-9]{8})[0-9]{2}".r

    accountNumber match {
      case accountNumberParser(firstEight) => AccountDigits.parse(sortCode, firstEight)
      case _                               => Left(coOperativeAndLeedsBuildingSocietyError)
    }
  }

}
