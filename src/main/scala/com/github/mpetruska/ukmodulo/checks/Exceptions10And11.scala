package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo._
import com.github.mpetruska.ukmodulo.digits.{AccountDigits, Weights}
import com.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exceptions10And11 {

  def check(accountDigits: AccountDigits, weightRow10: ModulusWeightRow, weightRow11: ModulusWeightRow): Either[Error, Boolean] = {
    EitherChecks.any(
      exception10Check(accountDigits, weightRow10),
      exception11Check(accountDigits, weightRow11)
    )
  }

  def exception10Check(accountDigits: AccountDigits, weightRow: ModulusWeightRow): Either[Error, Boolean] = {
    import AccountDigits._

    (for {
      a <- getDigit(accountDigits, 'a').right
      b <- getDigit(accountDigits, 'b').right
      g <- getDigit(accountDigits, 'g').right
    } yield (a, b, g) match {
      case (0, 9, 9) | (9, 9, 9) =>
        val newRow = weightRow.copy(weights = Weights.zeroiseUtoB(weightRow.weights))
        ModulusCheck.processStandard(accountDigits, newRow)
      case _ =>
        ModulusCheck.processStandard(accountDigits, weightRow)
    }).right.flatMap(identity)
  }

  def exception11Check(accountDigits: AccountDigits, weightRow: ModulusWeightRow): Either[Error, Boolean] = {
    ModulusCheck.processStandard(accountDigits, weightRow)
  }

}
