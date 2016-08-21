package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo._
import io.github.mpetruska.ukmodulo.digits.AccountDigits
import io.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exceptions12And13 {

  def check(accountDigits: AccountDigits, row1: ModulusWeightRow, row2: ModulusWeightRow): Either[Error, Boolean] = {
    EitherChecks.any(
      ModulusCheck.processStandard(accountDigits, row1),
      ModulusCheck.processStandard(accountDigits, row2)
    )
  }

}
