package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo._
import com.github.mpetruska.ukmodulo.digits.AccountDigits
import com.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exception3 {

  def check(accountDigits: AccountDigits, rowStandard: ModulusWeightRow, row3: ModulusWeightRow): Either[Error, Boolean] = {
    EitherChecks.all(
      standardCheck(accountDigits, rowStandard),
      exception3Check(accountDigits, row3)
    )
  }

  def standardCheck(accountDigits: AccountDigits, row: ModulusWeightRow): Either[Error, Boolean] = {
    ModulusCheck.processStandard(accountDigits, row)
  }

  def exception3Check(accountDigits: AccountDigits, row: ModulusWeightRow): Either[Error, Boolean] = {
    AccountDigits.getDigit(accountDigits, 'c')
      .map {
        case 6 | 9 => true
        case _     => DoubleAlternate.check(accountDigits, row.weights)
      }
  }

}
