package mrpolyonymous.problem3

class Problem3Test extends org.scalatest.FunSuite {

  test("Prime factorization not supported for Long < 2") {
    intercept[IllegalArgumentException] {
      Problem3.primeFactorization(-1L)
    }
    intercept[IllegalArgumentException] {
      Problem3.primeFactorization(0L)
    }
    intercept[IllegalArgumentException] {
      Problem3.primeFactorization(1L)
    }
  }

  test("Product of long factors equals original number") {
    for (x:Long <- 2L to 100L) {
      val factors = Problem3.primeFactorization(x)
      val product = factors.reduce(_ * _)
      assert(product == x, "Product was expected to be " + x + " but was " + product)
    }
  }

  test("Prime factorization not supported for BigInt < 2") {
    intercept[IllegalArgumentException] {
      Problem3.primeFactorization(BigInt(-1L))
    }
    intercept[IllegalArgumentException] {
      Problem3.primeFactorization(BigInt(0L))
    }
    intercept[IllegalArgumentException] {
      Problem3.primeFactorization(BigInt(1L))
    }
  }

  test("Product of BigInt factors equals original number") {
    for (x <- 2 to 100) {
      val factors:Seq[BigInt] = Problem3.primeFactorization(BigInt(x))
      val product = factors.reduce(_ * _)
      assert(product.intValue() == x, "Product was expected to be " + x + " but was " + product)
    }
  }

}
