package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo._
import com.github.mpetruska.ukmodulo.digits.AccountDigits
import com.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exceptions12And13 {

  def check(accountDigits: AccountDigits, row1: ModulusWeightRow, row2: ModulusWeightRow): Either[Error, Boolean] = {
    EitherChecks.any(
      ModulusCheck.processStandard(accountDigits, row1),
      ModulusCheck.processStandard(accountDigits, row2)
    )
  }

}
