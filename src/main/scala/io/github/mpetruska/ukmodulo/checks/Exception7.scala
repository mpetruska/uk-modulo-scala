package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo._
import io.github.mpetruska.ukmodulo.digits.{AccountDigits, Weights}
import io.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exception7 {

  def check(accountDigits: AccountDigits, weightRow: ModulusWeightRow): Either[Error, Boolean] = {
    AccountDigits.getDigit(accountDigits, 'g').right.flatMap {
      case 9 =>
        val newRow = weightRow.copy(weights = Weights.zeroiseUtoB(weightRow.weights))
        ModulusCheck.processStandard(accountDigits, newRow)
      case _ =>
        ModulusCheck.processStandard(accountDigits, weightRow)
    }
  }

}
