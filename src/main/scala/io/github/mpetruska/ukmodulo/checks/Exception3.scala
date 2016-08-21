package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo._
import io.github.mpetruska.ukmodulo.digits.AccountDigits
import io.github.mpetruska.ukmodulo.table.ModulusWeightRow

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
    AccountDigits.getDigit(accountDigits, 'c').right
      .map {
        case 6 | 9 => true
        case _     => DoubleAlternate.check(accountDigits, row.weights)
      }
  }

}
