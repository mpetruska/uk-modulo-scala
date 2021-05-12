package com.github.mpetruska.ukmodulo.table

import java.io.Reader

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.AccountDigits
import com.github.mpetruska.ukmodulo.table.ModulusWeightRowParser._

object ModulusWeightTable extends ResourceTable {

  type Row = ModulusWeightRow

  val resourcePath = "/valacdos-v660.txt"

  def parseAllRows(in: Reader): Either[Error, List[Row]] = {
    ModulusWeightRowParser.parseAllRows(in) match {
      case Success  (value, _) => Right(value)
      case Failure(error, _) => Left(error)
      case Error(error, _) => Left(error)
    }
  }

  def getWeightRowsFor(accountDigits: AccountDigits): Either[Error, List[ModulusWeightRow]] = {
    val accountNumber = AccountDigits.getSortCode(accountDigits)
    table.map { weightTable =>
      weightTable.filter(row => row.rangeStart <= accountNumber && row.rangeEnd >= accountNumber)
    }
  }

}
