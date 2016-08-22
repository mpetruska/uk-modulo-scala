package com.github.mpetruska.ukmodulo.java

import com.github.mpetruska.ukmodulo.ModulusCheck

class ModulusCheck {

  def check(sortCode: String, accountNumber: String): Boolean = {
    ModulusCheck.check(sortCode, accountNumber)
      .fold(error => throw new RuntimeException(error), identity)
  }

}
