package io.github.mpetruska.ukmodulo.checks

import io.github.mpetruska.ukmodulo.Error
import io.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}
import io.github.mpetruska.ukmodulo.table.SortCodeSubstitutionTable

object Exception5 {

  def check(accountDigits: AccountDigits, weightsMod11: Weights, weightsDblAl: Weights): Either[Error, Boolean] = {
    for {
      firstCheck <- mod11Check(accountDigits, weightsMod11).right
      secondCheck <- dblAllCheck(accountDigits, weightsDblAl).right
    } yield firstCheck && secondCheck
  }

  def mod11Check(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    import Standard11._

    for {
      replacedAccountDigits <- SortCodeSubstitutionTable.performSubstitutionFor(accountDigits).right
      g <- AccountDigits.getDigit(accountDigits, 'g').right
    } yield (calculateSum(replacedAccountDigits, weights) % modulus, g) match {
      case (0, 0)         => true
      case (1, _)         => false
      case (remainder, _) => 11 - remainder == g
    }
  }

  def dblAllCheck(accountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    import DoubleAlternate._
    AccountDigits.getDigit(accountDigits, 'h').right.map { h =>
      calculateSum(accountDigits, weights) % modulus match {
        case 0         => h == 0
        case remainder => 10 - remainder == h
      }
    }
  }

}
