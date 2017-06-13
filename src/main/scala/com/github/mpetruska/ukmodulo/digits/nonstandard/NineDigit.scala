package com.github.mpetruska.ukmodulo.digits.nonstandard

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.AccountDigits

object NineDigit {

  val santanderError = "Santander account numbers must be in format 123456789"

  def parseSantanderAccountNumber(sortCode: String, accountNumber: String): Either[Error, AccountDigits] = {
    val sortCodeParser      = "([0-9]{5})[0-9]".r
    val accountNumberParser = "([0-9])([0-9]{8})".r

    (sortCode, accountNumber) match {
      case (sortCodeParser(firstFive), accountNumberParser(first, lastEight)) => AccountDigits.parse(s"$firstFive$first", lastEight)
      case _                                                                  => Left(santanderError)
    }
  }


}
