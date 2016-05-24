package mrpolyonymous.problem3

import org.openjdk.jmh.annotations.Benchmark

class Problem3 {
  // Some JMH benchmarking of factorization methods.
  @Benchmark
  def longSmallFactorization(): Seq[Long] = {
    Problem3.primeFactorization(60)
  }

  @Benchmark
  def bigIntSmallFactorization(): Seq[BigInt] = {
    Problem3.primeFactorization(BigInt(60))
  }

  @Benchmark
  def longMediumFactorization(): Seq[Long] = {
    Problem3.primeFactorization(123456789123456789L)
  }

  @Benchmark
  def bigIntMediumFactorization(): Seq[BigInt] = {
    Problem3.primeFactorization(BigInt(123456789123456789L))
  }


}

/**
  *
  **
  *The prime factors of 13195 are 5, 7, 13 and 29.
  **
  *What is the largest prime factor of the number 600851475143 ?
  *
  */
object Problem3 {
  def main(args: Array[String]): Unit = {
    println("Two calculations of prime factorization of 123456789123456789")
    println(primeFactorization(123456789123456789L).reverse.mkString(" * "))
    println(primeFactorization(BigInt(123456789123456789L)).reverse.mkString(" * "))
  }

  // Factorizing algorithms seem to come in two flavors: simple and near-incomprehensible. Going with simple.
  /**
    * Return a sequence of the prime factors of the given number, from largest to smallest.
    * The product of the numbers in the sequence will multiply to the given number.
    * @param n
    *          The number to factor; must be at least 2
    * @return
    */
  def primeFactorization(n:Long):Seq[Long] = {
    if (n < 2L) {
      throw new IllegalArgumentException("Only implemented for numbers >= 2")
    }
    var factors:List[Long] = List[Long]()
    var currentN = n
    while ((currentN & 1L) == 0L) {
      factors = 2L :: factors
      currentN >>= 1
    }

    if (currentN > 1) {
      var factor = 3L
      while (factor < currentN) {
        if (currentN % factor == 0) {
          factors = factor :: factors
          currentN /= factor
        } else {
          factor += 2
        }
      }
      factors = factor :: factors
    }

    factors
  }

  private val zero = BigInt(0)
  private val one = BigInt(1)
  private val two = BigInt(2)
  // Factorizing algorithms seem to come in two flavors: simple and near-incomprehensible. Going with simple.
  /**
    * Return a sequence of the prime factors of the given number, from largest to smallest.
    * The product of the numbers in the sequence will multiply to the given number.
    * @param n
    *          The number to factor; must be at least 2
    * @return
    */
  def primeFactorization(n:BigInt):Seq[BigInt] = {
    if (n < two) {
      throw new IllegalArgumentException("Only implemented for numbers >= 2")
    }
    var factors:List[BigInt] = List[BigInt]()
    var currentN = n
    while (currentN.testBit(0) == false) {
      factors = two :: factors
      currentN >>= 1
    }

    if (currentN > one) {
      var factor = BigInt(3)
      while (factor < currentN) {
        if (currentN.mod(factor).equals(zero)) {
          factors = factor :: factors
          currentN /= factor
        } else {
          factor += two
        }
      }
      factors = factor :: factors
    }
    factors
  }

}
