package com.github.mpetruska.ukmodulo

import com.github.mpetruska.ukmodulo.checks._
import com.github.mpetruska.ukmodulo.digits.AccountDigits
import com.github.mpetruska.ukmodulo.digits.nonstandard._
import com.github.mpetruska.ukmodulo.table._

object ModulusCheck {

  val checkNotImplementedError = "Check not implemented for the given account number"

  def check(sortCode: String, accountNumber: String): Either[Error, Boolean] = {
    EitherChecks.any(
      Seq(
        AccountDigits.parse(sortCode, accountNumber),
        TenDigit.parseNationalWestminsterAccountNumber(sortCode, accountNumber),
        TenDigit.parseCoOperativeAccountNumber(sortCode, accountNumber),
        NineDigit.parseSantanderAccountNumber(sortCode, accountNumber),
        SevenDigit.parse(sortCode, accountNumber),
        SixDigit.parse(sortCode, accountNumber)
      ).map(checkParsed)
    )
  }

  def checkParsed(parseResult: Either[Error, AccountDigits]): Either[Error, Boolean] = {
    for {
      accountDigits <- parseResult.right
      isValid <- process(accountDigits).right
    } yield isValid
  }

  def process(accountDigits: AccountDigits): Either[Error, Boolean] = {
    ModulusWeightTable.getWeightRowsFor(accountDigits).right.flatMap(processWithWeights(accountDigits))
  }

  def processWithWeights(accountDigits: AccountDigits)(weightRows: List[ModulusWeightRow]): Either[Error, Boolean] = {
    weightRows.map(row => (row.exceptionCode, row)) match {
      case List() =>
        Right(true) // No modulus check can be performed, must assume account number is valid

      case List((Some(1), weightsRow)) if weightsRow.checkMethod == DblAl =>
        Right(Exception1.check(accountDigits, weightRows.head.weights))

      case List((Some(2), row2), (Some(9), row9)) if row2.checkMethod == Mod11 && row9.checkMethod == Mod11 =>
        Exceptions2And9.check(accountDigits, row2.weights, row9.weights)

      case List((None, row), (Some(3), rowDblAl)) if rowDblAl.checkMethod == DblAl =>
        Exception3.check(accountDigits, row, rowDblAl)

      case List((Some(4), row)) if row.checkMethod == Mod11 =>
        Exception4.performCheck(accountDigits, row.weights)

      case List((Some(5), mod11Row), (Some(5), dblalRow)) if mod11Row.checkMethod == Mod11 && dblalRow.checkMethod == DblAl =>
        Exception5.check(accountDigits, mod11Row.weights, dblalRow.weights)

      case List((Some(6), check1Row), (Some(6), check2Row)) =>
        Exception6.check(accountDigits, check1Row, check2Row)

      case List((Some(7), weightRow)) =>
        Exception7.check(accountDigits, weightRow)

      case List((Some(8), weightRow)) =>
        Exception8.check(accountDigits, weightRow)

      case List((Some(10), weightRow10), (Some(11), weightRow11)) =>
        Exceptions10And11.check(accountDigits, weightRow10, weightRow11)

      case List((Some(12), row1), (Some(13), row2)) =>
        Exceptions12And13.check(accountDigits, row1, row2)

      case List((Some(14), row)) =>
        Exception14.check(accountDigits, row.weights)

      case standardChecks if standardChecks.forall(_._1.isEmpty) =>
        EitherChecks.all(
          standardChecks.map{
            case (_, row) => processStandard(accountDigits, row)
          }: _*
        )

      case _ =>
        Left(checkNotImplementedError)
    }
  }

  def processStandard(accountDigits: AccountDigits, weightRow: ModulusWeightRow): Either[Error, Boolean] = weightRow.checkMethod match {
    case DblAl => Right(DoubleAlternate.check(accountDigits, weightRow.weights))
    case Mod10 => Right(Standard10.check(accountDigits, weightRow.weights))
    case Mod11 => Right(Standard11.check(accountDigits, weightRow.weights))
  }

}
