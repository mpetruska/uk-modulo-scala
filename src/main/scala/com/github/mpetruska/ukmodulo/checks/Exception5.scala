package com.github.mpetruska.ukmodulo.checks

import com.github.mpetruska.ukmodulo.Error
import com.github.mpetruska.ukmodulo.digits.{Weights, AccountDigits}
import com.github.mpetruska.ukmodulo.table.SortCodeSubstitutionTable

object Exception5 {

  def check(accountDigits: AccountDigits, weightsMod11: Weights, weightsDblAl: Weights): Either[Error, Boolean] = {
    SortCodeSubstitutionTable.performSubstitutionFor(accountDigits).flatMap { replacedAccountDigits =>
      EitherChecks.all(
        mod11Check(replacedAccountDigits, weightsMod11),
        dblAllCheck(replacedAccountDigits, weightsDblAl)
      )
    }
  }

  def mod11Check(replacedAccountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    import Standard11._

    AccountDigits.getDigit(replacedAccountDigits, 'g').map { g =>
      (calculateSum(replacedAccountDigits, weights) % modulus, g) match {
        case (0, 0)         => true
        case (1, _)         => false
        case (remainder, _) => 11 - remainder == g
      }
    }
  }

  def dblAllCheck(replacedAccountDigits: AccountDigits, weights: Weights): Either[Error, Boolean] = {
    import DoubleAlternate._
    AccountDigits.getDigit(replacedAccountDigits, 'h').map { h =>
      calculateSum(replacedAccountDigits, weights) % modulus match {
        case 0         => h == 0
        case remainder => 10 - remainder == h
      }
    }
  }

}
