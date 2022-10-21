package com.github.mpetruska.ukmodulo.table

import java.io.InputStreamReader

import com.github.mpetruska.ukmodulo.digits.Weights
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ModulusWeightRowParserSpec extends AnyWordSpec with Matchers {

  "ModulusWeightRowParser" should {
    import ModulusWeightRowParser._

    "be able to parse the weights table" in {
      val parseResult = parseAllRows(new InputStreamReader(ModulusWeightRowParser.getClass.getResourceAsStream("/valacdos-v730.txt")))
      parseResult.successful shouldBe true
      
      val rows = parseResult.get
      rows.length should be > 100
      rows.head   shouldBe ModulusWeightRow("010004", "016715", Mod11, Weights(Vector(0, 0, 0, 0, 0, 0, 8, 7, 6, 5, 4, 3, 2, 1)), None)
      rows.find(_.exceptionCode.isDefined) shouldBe
        Some(ModulusWeightRow("070116", "070116", Mod11, Weights(Vector(0, 0, 7, 6, 5, 8, 9, 4, 5, 6, 7, 8, 9, -1)), Some(12)))
    }

  }

}
