# Factorization

## Unit tests

Time to try out some unit tests. scalatest seems to be the first choice
but it wasn't as trivial to integrate as I'd hoped. Something about
the combination of SBT, JMH, and the scalatest instructions didn't
work and it took some experimentation to get something that worked.

## Performance tests

Nothing shocking in the test results; dealing with Long directly is
faster than BigInt. Scala's BigInt feels more natural to work with
than Java' BigInteger.

## References

1. [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)
2. [scalatest](http://www.scalatest.org/)