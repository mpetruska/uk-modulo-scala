package com.github.mpetruska.ukmodulo.table

import java.io.Reader

import com.github.mpetruska.ukmodulo.digits.Weights

import scala.util.parsing.combinator.RegexParsers

object ModulusWeightRowParser extends RegexParsers {

  override val whiteSpace = """[ \t]+""".r

  def sortCode: Parser[String]           = "[0-9]{6}".r
  def dblal: Parser[DblAl.type]          = "DBLAL" ^^ (_ => DblAl)
  def mod10: Parser[Mod10.type]          = "MOD10" ^^ (_ => Mod10)
  def mod11: Parser[Mod11.type]          = "MOD11" ^^ (_ => Mod11)
  def checkMethod: Parser[CheckMethod]   = dblal | mod10 | mod11
  def number: Parser[Int]                = "-?[0-9]+".r ^^ (_.toInt)
  def weights: Parser[Weights]           = repN(14, number) ^^ (list => Weights(list.toVector))
  def exceptionCode: Parser[Option[Int]] = number ^^ Some.apply | whiteSpace ^^ (_ => None) | "" ^^ (_ => None)
  def newLine: Parser[Unit]              = """(\r?\n)+""".r ^^ (_ => ())

  def modulusWeightRow: Parser[ModulusWeightRow] = {
    sortCode ~ sortCode ~ checkMethod ~ weights ~ exceptionCode ^^ {
      case start ~ end ~ check ~ w ~ exc => ModulusWeightRow(start, end, check, w, exc)
    }
  }

  def modulusWeightRows: Parser[List[ModulusWeightRow]] = repsep(modulusWeightRow, newLine) <~ opt(newLine)

  def parseAllRows(in: Reader): ParseResult[List[ModulusWeightRow]] =
    parseAll(modulusWeightRows, in)

}
