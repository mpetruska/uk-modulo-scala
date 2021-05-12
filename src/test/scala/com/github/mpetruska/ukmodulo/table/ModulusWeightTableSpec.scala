package com.github.mpetruska.ukmodulo.table

import com.github.mpetruska.ukmodulo.digits._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ModulusWeightTableSpec extends AnyWordSpec with Matchers {

  val accountDigits1 = AccountDigits(Vector(2, 0, 0, 0, 0, 0) ++ Vector.fill(8)(0))
  val accountDigits2 = AccountDigits(Vector(2, 0, 0, 0, 0, 1) ++ (1 to 8).toVector)
  val accountDigits3 = AccountDigits(Vector(2, 0, 0, 0, 0, 2) ++ Vector.fill(8)(9))

  val expectedWeightRows = List(
    ModulusWeightRow("200000", "200002", Mod11, Weights(Vector(0, 0, 0, 0, 0, 0, 0, 7, 6, 5, 4, 3, 2, 1)), Some(6)),
    ModulusWeightRow("200000", "200002", DblAl, Weights(Vector(2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1)), Some(6))
  )

  "ModulusWeightTable" should {

    "filter relevant rows correctly" in {
      ModulusWeightTable.getWeightRowsFor(accountDigits1) shouldBe Right(expectedWeightRows)
      ModulusWeightTable.getWeightRowsFor(accountDigits2) shouldBe Right(expectedWeightRows)
      ModulusWeightTable.getWeightRowsFor(accountDigits3) shouldBe Right(expectedWeightRows)
    }

  }

}
