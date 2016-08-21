package io.github.mpetruska.ukmodulo.table

import java.io.{Reader, InputStreamReader}

import io.github.mpetruska.ukmodulo.Error

import scala.util.Try

trait ResourceTable {

  def resourcePath: String

  def resourceReadError = s"Could not load resource file $resourcePath"

  type Row
  def parseAllRows(in: Reader): Either[Error, List[Row]]

  lazy val table: Either[Error, List[Row]] =
    Try(parseAllRows(new InputStreamReader(ModulusWeightRowParser.getClass.getResourceAsStream(resourcePath))))
      .getOrElse(Left(resourceReadError))

}
