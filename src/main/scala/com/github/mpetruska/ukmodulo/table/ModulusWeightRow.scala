package com.github.mpetruska.ukmodulo.table

import com.github.mpetruska.ukmodulo.digits.Weights

sealed trait CheckMethod
case object DblAl extends CheckMethod
case object Mod10 extends CheckMethod
case object Mod11 extends CheckMethod

case class ModulusWeightRow(rangeStart: String, rangeEnd: String, checkMethod: CheckMethod, weights: Weights, exceptionCode: Option[Int])
