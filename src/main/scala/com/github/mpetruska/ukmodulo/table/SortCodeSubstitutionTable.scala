package com.github.mpetruska.ukmodulo.table

import java.io.Reader

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.AccountDigits
import com.github.mpetruska.ukmodulo.table.SortCodeSubstitutionRowParser._

object SortCodeSubstitutionTable extends ResourceTable {

  type Row = SortCodeSubstitutionRow

  val resourcePath = "/scsubtab.txt"

  def parseAllRows(in: Reader): Either[Error, List[Row]] = {
    SortCodeSubstitutionRowParser.parseAllRows(in) match {
      case Success(value, _)   => Right(value)
      case NoSuccess(error, _) => Left(error)
    }
  }

  def performSubstitutionFor(accountDigits: AccountDigits): Either[Error, AccountDigits] = {
    table.right.flatMap { substitutionTable =>
      val originalSortCode = AccountDigits.getSortCode(accountDigits)
      substitutionTable.find(_.originalSortCode == originalSortCode) match {
        case None             => Right(accountDigits)
        case Some(substitute) => AccountDigits.parse(substitute.substitution, AccountDigits.getAccountNumber(accountDigits))
      }
    }
  }

}
