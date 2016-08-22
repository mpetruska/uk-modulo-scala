package com.github.mpetruska.ukmodulo.java

import com.github.mpetruska.ukmodulo.ModulusCheck

case class ModulusCheckResult(isError: Boolean, isValidAccountNumber: Boolean, error: String = "")

class ModulusCheck {

  def check(sortCode: String, accountNumber: String): ModulusCheckResult = {
    ModulusCheck.check(sortCode, accountNumber)
      .fold(
        error  => ModulusCheckResult(isError = true,  isValidAccountNumber = false,  error = error),
        result => ModulusCheckResult(isError = false, isValidAccountNumber = result)
      )
  }

}
