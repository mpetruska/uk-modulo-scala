package com.github.mpetruska.ukmodulo

import com.github.mpetruska.ukmodulo.digits.AccountDigits
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class ModulusCheckSpec extends WordSpec with TableDrivenPropertyChecks with Matchers {

  val specificationTestData = Table(
    ("testNumber", "description", "sortCode", "accountNumber", "result"),
    (1, "Pass modulus 10 check.", "089999", "66374958", true),
    (2, "Pass modulus 11 check.", "107999", "88837491", true),
    (3, "Pass modulus 11 and double alternate checks.", "202959", "63748472", true),
    (4, "Exception 10 & 11 where first check passes and second check fails.", "871427", "46238510", true),
    (5, "Exception 10 & 11 where first check fails and second check passes.", "872427", "46238510", true),
    (6, "Exception 10 where in the account number ab=09 and the g=9. The first check passes and second check fails.", "871427", "09123496", true),
    (7, "Exception 10 where in the account number ab=99 and the g=9. The first check passes and the second check fails.", "871427", "99123496", true),
    (8, "Exception 3, and the sorting code is the start of a range. As c=6 the second check should be ignored.", "820000", "73688637", true),
    (9, "Exception 3, and the sorting code is the end of a range. As c=9 the second check should be ignored.", "827999", "73988638", true),
    (10, "Exception 3. As c<>6 or 9 perform both checks pass.", "827101", "28748352", true),
    (11, "Exception 4 where the remainder is equal to the checkdigit.", "134020", "63849203", true),
    (12, "Exception 1 – ensures that 27 has been added to the accumulated total and passes double alternate modulus check.", "118765", "64371389", true),
    (13, "Exception 6 where the account fails standard check but is a foreign currency account.", "200915", "41011166", true),
    (14, "Exception 5 where the check passes.", "938611", "07806039", true),
    (15, "Exception 5 where the check passes with substitution.", "938600", "42368003", true),
    (16, "Exception 5 where both checks produce a remainder of 0 and pass.", "938063", "55065200", true),
    (17, "Exception 7 where passes but would fail the standard check.", "772798", "99345694", true),
    (18, "Exception 8 where the check passes.", "086090", "06774744", true),
    (19, "Exception 2 & 9 where the first check passes.", "309070", "02355688", true),
    (20, "Exception 2 & 9 where the first check fails and second check passes with substitution.", "309070", "12345668", true),
    (21, "Exception 2 & 9 where a≠0 and g≠9 and passes.", "309070", "12345677", true),
    (22, "Exception 2 & 9 where a≠0 and g=9 and passes.", "309070", "99345694", true),
    (23, "Exception 5 where the first checkdigit is correct and the second incorrect.", "938063", "15764273", false),
    (24, "Exception 5 where the first checkdigit is incorrect and the second correct.", "938063", "15764264", false),
    (25, "Exception 5 where the first checkdigit is incorrect with a remainder of 1.", "938063", "15763217", false),
    (26, "Exception 1 where it fails double alternate check.", "118765", "64371388", false),
    (27, "Pass modulus 11 check and fail double alternate check.", "203099", "66831036", false),
    (28, "Fail modulus 11 check and pass double alternate check.", "203099", "58716970", false),
    (29, "Fail modulus 10 check.", "089999", "66374959", false),
    (30, "Fail modulus 11 check.", "107999", "88837493", false),
    (31, "Exception 12/13 where passes modulus 11 check (in this example, modulus 10 check fails, however, there is no need for it to be performed as the first check passed).", "074456", "12345112", true),
    (32, "Exception 12/13 where passes the modulus 11check (in this example, modulus 10 check passes as well, however, there is no need for it to be performed as the first check passed).", "070116", "34012583", true),
    (33, "Exception 12/13 where fails the modulus 11 check, but passes the modulus 10 check.", "074456", "11104102", true),
    (34, "Exception 14 where the first check fails and the second check passes.", "180002", "00000190", true)
  )

  val additionalTestData = Table(
    ("sortCode", "accountNumber", "shouldPass"),
    ("404784",   "70872490",      true),
    ("404784",   "70872491",      false),
    ("205132",   "13537846",      true),
    ("205132",   "23537846",      false),
    ("090128",   "03745521",      true),
    ("090128",   "13745521",      false),
    ("560003",   "13354647",      true),
    ("560003",   "23354647",      false),
    ("308087",   "25337846",      false),
    ("308088",   "14457846",      true),
    ("308088",   "24457846",      false)
  )

  "ModulusCheck" should {

    "pass the tests provided by the specification" in {
      forAll(specificationTestData) { (_: Int, _: String, sortCode: String, accountNumber: String, result: Boolean) =>
        ModulusCheck.check(sortCode, accountNumber) shouldBe Right(result)
      }
    }

    "pass additional test data" in {
      forAll(additionalTestData) { (sortCode: String, accountNumber: String, shouldPass: Boolean) =>
        ModulusCheck.check(sortCode, accountNumber) shouldBe Right(shouldPass)
      }
    }

  }

}
