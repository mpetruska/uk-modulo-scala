package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo._
import com.github.mpetruska.ukmodulo.digits.AccountDigits
import com.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exception8 {

  val sortCodeReplacement = "090126"

  def check(accountDigits: AccountDigits, weightRow: ModulusWeightRow): Either[Error, Boolean] = {
    AccountDigits.replaceSortCode(accountDigits, sortCodeReplacement).flatMap { replacedAccountDigits =>
      ModulusCheck.processStandard(replacedAccountDigits, weightRow)
    }
  }

}
