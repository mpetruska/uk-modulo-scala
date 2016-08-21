package io.github.mpetruska.ukmodulo

import io.github.mpetruska.ukmodulo.checks._
import io.github.mpetruska.ukmodulo.digits.AccountDigits
import io.github.mpetruska.ukmodulo.table._

object ModulusCheck {

  val checkNotImplementedError = "Check not implemented for the given account number"

  def process(accountDigits: AccountDigits): Either[Error, Boolean] = {
    ModulusWeightTable.getWeightRowsFor(accountDigits).right.flatMap(processWithWeights(accountDigits))
  }

  def processWithWeights(accountDigits: AccountDigits)(weightRows: List[ModulusWeightRow]): Either[Error, Boolean] = {
    weightRows.map(row => (row.exceptionCode, row)) match {
      case List() =>
        Right(true) // No modulus check can be performed, must assume account number is valid

      case List((None, weightsRow)) =>
        processStandard(accountDigits, weightsRow)

      case List((Some(1), weightsRow)) if weightsRow.checkMethod == DblAl =>
        Right(Exception1.check(accountDigits, weightRows.head.weights))

      case List((Some(2), row2), (Some(9), row9)) if row2.checkMethod == Mod11 && row9.checkMethod == Mod11 =>
        println(row2)
        println(row9)
        Exceptions2And9.check(accountDigits, row2.weights, row9.weights)

      case List((Some(3), row)) if row.checkMethod == DblAl =>
        Exception3.performCheck(accountDigits, row.weights)

      case List((Some(4), row)) if row.checkMethod == Mod11 =>
        Exception4.performCheck(accountDigits, row.weights)

      case List((Some(5), mod11Row), (Some(5), dblalRow)) if mod11Row.checkMethod == Mod11 && dblalRow.checkMethod == DblAl =>
        Exception5.check(accountDigits, mod11Row.weights, dblalRow.weights)

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
