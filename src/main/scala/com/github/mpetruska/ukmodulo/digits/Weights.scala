package com.github.mpetruska.ukmodulo.digits

case class Weights private[ukmodulo] (values: Vector[Int]) {
  require(values.length == 14)
}

object Weights {

  def zeroiseUtoB(weights: Weights): Weights =
    Weights(Vector.fill(8)(0) ++ weights.values.drop(8))

  def dotMul(weights: Weights, accountDigits: AccountDigits): Weights =
    dotMul(weights.values, accountDigits.digits)

  def dotMul(left: Weights, right: Weights): Weights =
    dotMul(left.values, right.values)

  def sum(weights: Weights): Int =
    weights.values.sum

  def sumDigits(weights: Weights): Int =
    weights.values.map(_.toString.map(_.asDigit).sum).sum

  private def dotMul(left: Vector[Int], right: Vector[Int]): Weights = {
    val mul: (Int, Int) => Int = _ * _

    Weights(left.zip(right).map(mul.tupled))
  }

}
