[![Build Status](https://travis-ci.org/mpetruska/uk-modulo-scala.svg?branch=master)](https://travis-ci.org/mpetruska/uk-modulo-scala)
[![Known Vulnerabilities](https://snyk.io/test/github/mpetruska/uk-modulo-scala/badge.svg?targetFile=build.sbt)](https://snyk.io/test/github/mpetruska/uk-modulo-scala?targetFile=build.sbt)

UK modulo - Scala
=================

This is an implementation of the [VocaLink UK Bank account number
modulus checking][VocaLink link] version 7.10 (and previous versions), written in Scala.

[VocaLink link]: https://www.vocalink.com/tools/modulus-checking/

Modulus checking is a procedure used to determine whether a bank account number
can be valid. If the account number check is negative then the account cannot
exist, but the opposite is not true (meaning that if the check succeeds that does
not guarantee the existence of the account).

Modulus checking can be used to help detect some input errors, but
unfortunately there can be user errors that remain undetected.

License: [MIT](LICENSE)

Notes on validating sort codes
------------------------------

The ["Industry Sorting Code Directory" (ISCD)][ICSD link]
should be used to validate UK sort codes.

[ICSD link]: https://en.wikipedia.org/wiki/Industry_Sorting_Code_Directory

Getting started
---------------

Sbt:

    libraryDependencies += "com.github.mpetruska" %% "uk-modulo-scala" % "7.10.0"

pom.xml:

    <dependency>
      <groupId>com.github.mpetruska</groupId>
      <artifactId>uk-modulo-scala_2.13</artifactId>
      <version>7.10.0</version>
    </dependency>

Usage
-----

Scala:

```Scala
import com.github.mpetruska.ukmodulo.ModulusCheck

// valid account number
ModulusCheck.check(sortCode = "089999", accountNumber = "66374958") === Right(true)

// invalid account number
ModulusCheck.check(sortCode = "089999", accountNumber = "66374959") === Right(false)

// invalid format
ModulusCheck.check(sortCode = "089999", accountNumber = "xxxx") === Left("Account number format is not valid")
```

Java:

```Java
import com.github.mpetruska.ukmodulo.java.ModulusCheck;
import com.github.mpetruska.ukmodulo.java.ModulusCheckResult;

// valid account number
new ModulusCheck().check("089999", "66374958").isValidAccountNumber() == true;

// invalid account number
ModulusCheckResult result = new ModulusCheck().check("089999", "66374959");
result.isValidAccountNumber() == false;
result.isError() == false;

// invalid format
ModulusCheckResult result2 = new ModulusCheck().check("089999", "xxxx");
result2.isError() == true;
result2.error() == "Account number format is not valid";
```

Issues
------

Please report issues and feature requests [here](https://github.com/mpetruska/uk-modulo-scala/issues).

Version history
---------------

* 7.10.0 - updates implementation according to [version 7.10 of the spec](https://www.vocalink.com/media/2vtny3ie/validating-account-numbers-v7-1.pdf)
  (valid from 2022-03-21)
* 6.90.0 - updates implementation according to [version 6.90 of the spec](https://www.vocalink.com/media/000dcpzj/vocalink-validating-account-numbers-v6-90.pdf)
  (valid from 2022-02-14)
* 6.80.0 - updates implementation according to [version 6.80 of the spec](https://www.vocalink.com/media/3allk2nz/vocalink-validating-account-numbers-v6-80.pdf)
  (valid from 2021-10-04)
* 6.60.0 - add Scala 2.13, remove Scala 2.10 and 2.11. Also updates implementation according to [version 6.60 of the spec](https://www.vocalink.com/media/4940/vocalink-validating-account-numbers-v660.pdf)
  (valid from 2021-04-26)
* 6.12.0 - updates implementation according to [version 6.12 of the spec](https://www.vocalink.com/media/4475/vocalink-validating-account-numbers-v612.pdf)
  (valid from 2020-07-14)
* 6.1.0  - updates implementation according to [version 6.1 of the spec](https://www.vocalink.com/media/4373/vocalink-validating-account-numbers-v61.pdf)
  (valid from 2020-04-07)
* 6.00.1 - fixes implicit change in the meaning of exception code 6
* 6.00.0 - updates implementation according to [version 6.00 of the spec](https://www.vocalink.com/media/4363/vocalink-validating-account-numbers-v600.pdf)
  (valid from 2020-04-06)
* 5.90.0 - updates implementation according to [version 5.90 of the spec](https://www.vocalink.com/media/4325/vocalink-validating-account-numbers-v590.pdf)
  (valid from 2020-02-03)
* 5.80.0 - updates implementation according to [version 5.80 of the spec](https://www.vocalink.com/media/3513/vocalink-validating-account-numbers-v580.pdf)
  (valid from 2019-09-30)
* 5.70.0 - updates implementation according to [version 5.70 of the spec](https://www.vocalink.com/media/3103/vocalink-validating-account-numbers-v570.pdf)
  (valid from 2019-05-27)
* 5.50.0 - updates implementation according to [version 5.50 of the spec](https://www.vocalink.com/media/3068/vocalink-validating-account-numbers-v550.pdf)
  (valid from 2019-03-18)
* 5.40.0 - updates implementation according to [version 5.40 of the spec](https://www.vocalink.com/media/3061/vocalink-validating-account-numbers-v540.pdf)
  (valid from 2019-01-28)
* 5.30.0 - updates implementation according to [version 5.30 of the spec](https://www.vocalink.com/media/3047/vocalink-validating-account-numbers-v530.pdf)
  (valid from 2018-11-26)
* 5.20.0 - updates implementation according to [version 5.20 of the spec](https://www.vocalink.com/media/3038/validating-account-numbers-v520.pdf)
  (valid from 2018-10-22)
* 5.10.0 - updates implementation according to [version 5.10 of the spec](https://www.vocalink.com/media/3035/validating-account-numbers-v510.pdf)
  (valid from 2018-09-17)
* 5.0.0 - updates implementation according to [version 5.00 of the spec](https://www.vocalink.com/media/3019/vocalink-validating-account-numbers-v500.pdf)
  (valid from 2018-08-06)
* 1.9.0 - updates implementation according to [version 4.90 of the spec](https://www.vocalink.com/media/3004/vocalink-validating-account-numbers-v490.pdf)
  (valid from 2018-07-02)
* 1.8.0 - updates implementation according to [version 4.80 of the spec](https://www.vocalink.com/media/2920/vocalink-validating-account-numbers-v480.pdf)
  (valid from 2018-04-16)
* 1.7.0 - updates implementation according to [version 4.70 of the spec](https://www.vocalink.com/media/2904/vocalink-validating-account-numbers-v47.pdf)
  (valid from 2018-03-26)
* 1.6.0 - updates implementation according to [version 4.60 of the spec](https://www.vocalink.com/media/2771/vocalink-validating-account-numbers-v460.pdf)
  (valid from 2017-10-09)
* 1.5.0 - updates implementation according to [version 4.40 of the spec](https://www.vocalink.com/media/2717/vocalink-validating-account-numbers-v440.pdf)
  (valid from 2017-08-21)
* 1.4.0 - updates implementation according to [version 4.30 of the spec](https://www.vocalink.com/media/2467/vocalink-validating-account-numbers-v430.pdf)
  (valid from 2017-07-03)
* 1.3.0 - updates implementation according to [version 4.20 of the spec](https://www.vocalink.com/media/2434/vocalink-validating-account-numbers-v420.pdf)
  (valid from 2017-06-12)
* 1.2.0 - updates implementation according to [version 4.10 of the spec](https://www.vocalink.com/media/2295/vocalink-validating-account-numbers-v410.pdf)
  (valid from 2017-03-06)
* 1.1.0 - updates implementation according to [version 4.00 of the spec](https://www.vocalink.com/media/2101/vocalink-validating-account-numbers-v400.pdf)
  (valid from 2017-01-09)
* 1.0.2 - adds Scala 2.12 to the released artifacts
* 1.0.1 - better support for plain Java
* 1.0.0 - initial release, spec version 3.90
