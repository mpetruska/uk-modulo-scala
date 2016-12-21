[![Build Status](https://travis-ci.org/mpetruska/uk-modulo-scala.svg?branch=master)](https://travis-ci.org/mpetruska/uk-modulo-scala)

UK modulo - Scala
=================

This is an implementation of the [VocaLink UK Bank account number
modulus checking][VocaLink link] version 3.90, written in Scala.

[VocaLink link]: https://www.vocalink.com/customer-support/modulus-checking/

Modulus checking is a procedure used to determine whether a bank account number
can be valid. If the account number check is negative then the account cannot
exist, be the opposite is not true (meaning that if the check succeeds that does
not guarantee the existence of the account).

Based on this modulus checking can be used to help detect some input errors, but
unfortunately there can be errors that are undetected.

License: [MIT](LICENSE)

Getting started
---------------

Sbt:

    libraryDependencies += "com.github.mpetruska" %% "uk-modulo-scala" % "1.0.2"

pom.xml:

    <dependency>
      <groupId>com.github.mpetruska</groupId>
      <artifactId>uk-modulo-scala_2.11</artifactId>
      <version>1.0.2</version>
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

1.0.2 - adds Scala 2.12 to the released artifacts
1.0.1 - better support for plain Java
1.0.0 - initial release
