package io.github.mpetruska.ukmodulo.table

import java.io.InputStreamReader

import io.github.mpetruska.ukmodulo.Error
import io.github.mpetruska.ukmodulo.digits.AccountDigits
import io.github.mpetruska.ukmodulo.table.ModulusWeightRowParser._

import scala.util.Try

object ModulusWeightTable {

  val sortCodeLength = 6
  val resourcePath = "/valacdos-v390.txt"

  val resourceReadError = s"Could not load resource file $resourcePath"

  def parseError(reason: String) = s"Could not parse resource file $resourcePath, reason: $reason"

  val weightTable: Either[Error, List[ModulusWeightRow]] =
    Try(parseAllRows(new InputStreamReader(ModulusWeightRowParser.getClass.getResourceAsStream(resourcePath))))
        .map {
          case Success(table, _)   => Right(table)
          case NoSuccess(reason, _) => Left(parseError(reason))
        }
        .getOrElse(Left(resourceReadError))

  def getWeightRowsFor(accountDigits: AccountDigits): Either[Error, List[ModulusWeightRow]] = {
    val accountNumber = accountDigits.digits.take(sortCodeLength).mkString
    weightTable.right.map { table =>
      table.filter(row => row.rangeStart <= accountNumber && row.rangeEnd >= accountNumber)
    }
  }

}
