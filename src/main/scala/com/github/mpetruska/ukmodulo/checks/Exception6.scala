package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo._
import com.github.mpetruska.ukmodulo.digits.AccountDigits
import com.github.mpetruska.ukmodulo.table.ModulusWeightRow

object Exception6 {

  def check(accountDigits: AccountDigits, rows: Seq[ModulusWeightRow]): Either[Error, Boolean] = {
    import AccountDigits._
    (for {
      a <- getDigit(accountDigits, 'a')
      g <- getDigit(accountDigits, 'g')
      h <- getDigit(accountDigits, 'h')
    } yield (a, g == h) match {
      case (4, true) | (5, true) | (6, true) | (7, true) | (8, true) => Right(true)
      case _                                                         => checkStandard(accountDigits, rows)
    }).flatMap(identity)
  }

  def checkStandard(accountDigits: AccountDigits, rows: Seq[ModulusWeightRow]): Either[Error, Boolean] = {
    import ModulusCheck._

    EitherChecks.all(
      rows.map(processStandard(accountDigits, _)): _*
    )
  }

}
