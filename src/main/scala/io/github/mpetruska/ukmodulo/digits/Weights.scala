package io.github.mpetruska.ukmodulo.digits

case class Weights(values: Vector[Int]) {
  require(values.length == 14)
}

object Weights {

  def dotMul(weights: Weights, accountDigits: AccountDigits): Weights = dotMul(weights.values, accountDigits.digits)

  def dotMul(left: Weights, right: Weights): Weights = dotMul(left.values, right.values)

  def sum(weights: Weights): Int = weights.values.sum

  def sumDigits(weights: Weights): Int = {
    val addDigits: (Int, Int) => Int = _ + _.toString.map(_.asDigit).sum

    (0 /: weights.values) (addDigits)
  }

  private def dotMul(left: Vector[Int], right: Vector[Int]): Weights = {
    val mul: (Int, Int) => Int = _ * _

    Weights(left.zip(right).map(mul.tupled))
  }

}
