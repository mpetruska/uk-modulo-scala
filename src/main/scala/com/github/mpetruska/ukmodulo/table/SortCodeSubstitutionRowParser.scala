package com.github.mpetruska.ukmodulo.table

import java.io.Reader

import scala.util.parsing.combinator.RegexParsers

object SortCodeSubstitutionRowParser extends RegexParsers {

  override val whiteSpace      = """[ \t]+""".r

  def sortCode: Parser[String] = "[0-9]{6}".r
  def newLine:  Parser[Unit]   = """(\r?\n)+""".r ^^ (_ => ())

  def sortCodeSubstitutionRow: Parser[SortCodeSubstitutionRow] = sortCode ~ sortCode ^^ {
    case original ~ replacement => SortCodeSubstitutionRow(original, replacement)
  }

  def sortCodeSubstitutionRows: Parser[List[SortCodeSubstitutionRow]] = repsep(sortCodeSubstitutionRow, newLine) <~ opt(newLine)

  def parseAllRows(in: Reader): ParseResult[List[SortCodeSubstitutionRow]] =
    parseAll(sortCodeSubstitutionRows, in)

}
