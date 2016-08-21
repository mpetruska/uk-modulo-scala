package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo._
import io.github.mpetruska.ukmodulo.digits.AccountDigits
import io.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exception6 {

  def check(accountDigits: AccountDigits, row1: ModulusWeightRow, row2: ModulusWeightRow): Either[Error, Boolean] = {
    import AccountDigits._
    (for {
      a <- getDigit(accountDigits, 'a').right
      g <- getDigit(accountDigits, 'g').right
      h <- getDigit(accountDigits, 'h').right
    } yield (a, g == h) match {
      case (4, true) | (5, true) | (6, true) | (7, true) | (8, true) => Right(true)
      case _ => checkStandard(accountDigits, row1, row2)
    }).right.flatMap(identity)
  }

  def checkStandard(accountDigits: AccountDigits, row1: ModulusWeightRow, row2: ModulusWeightRow): Either[Error, Boolean] = {
    import ModulusCheck._

    EitherChecks.all(
      processStandard(accountDigits, row1),
      processStandard(accountDigits, row2)
    )
  }

}
