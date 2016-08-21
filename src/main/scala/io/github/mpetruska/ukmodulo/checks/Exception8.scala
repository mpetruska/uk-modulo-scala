package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo._
import io.github.mpetruska.ukmodulo.digits.AccountDigits
import io.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exception8 {

  val sortCodeReplacement = "090126"

  def check(accountDigits: AccountDigits, weightRow: ModulusWeightRow): Either[Error, Boolean] = {
    AccountDigits.replaceSortCode(accountDigits, sortCodeReplacement).right.flatMap { replacedAccountDigits =>
      ModulusCheck.processStandard(replacedAccountDigits, weightRow)
    }
  }

}
