package io.github.mpetruska.ukmodulo.table

import java.io.Reader

import io.github.mpetruska.ukmodulo.Error
import io.github.mpetruska.ukmodulo.digits.AccountDigits
import io.github.mpetruska.ukmodulo.table.ModulusWeightRowParser._

object ModulusWeightTable extends ResourceTable {

  type Row = ModulusWeightRow

  val resourcePath = "/valacdos-v390.txt"

  def parseAllRows(in: Reader): Either[Error, List[Row]] = {
    ModulusWeightRowParser.parseAllRows(in) match {
      case Success(value, _)   => Right(value)
      case NoSuccess(error, _) => Left(error)
    }
  }

  def getWeightRowsFor(accountDigits: AccountDigits): Either[Error, List[ModulusWeightRow]] = {
    val accountNumber = AccountDigits.getSortCode(accountDigits)
    table.right.map { weightTable =>
      weightTable.filter(row => row.rangeStart <= accountNumber && row.rangeEnd >= accountNumber)
    }
  }

}
