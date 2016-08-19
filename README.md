
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

More coming soon...
