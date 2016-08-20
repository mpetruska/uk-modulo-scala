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
    weightRows.map(_.exceptionCode).sorted match {
      case List()     => Right(true) // No modulus check can be performed, must assume account number is valid
      case List(None) => processStandard(accountDigits, weightRows.head)

      case _          => Left(checkNotImplementedError)
    }
  }

  def processStandard(accountDigits: AccountDigits, weightRow: ModulusWeightRow): Either[Error, Boolean] = weightRow.checkMethod match {
    case DblAl => Right(DoubleAlternate.check(accountDigits, weightRow.weights))
    case Mod10 => Right(Standard10.check(accountDigits, weightRow.weights))
    case Mod11 => Right(Standard11.check(accountDigits, weightRow.weights))
  }

}
