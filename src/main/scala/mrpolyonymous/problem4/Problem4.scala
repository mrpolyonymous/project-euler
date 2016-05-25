package mrpolyonymous.problem4

import org.openjdk.jmh.annotations.Benchmark


/**
  */
class Problem4 {
  // Benchmarking. The for/yield variant isn't as slow as I expected, and the while-based variant is
  // slower than expected.
  @Benchmark
  def bruteForceWithYield(): Long = {
    Problem4.largestPalindromeProductBruteForceWithYield(100, 500)
  }

  @Benchmark
  def bruteForceWithWhile(): Long = {
    Problem4.largestPalindromeProductBruteForceWithWhile(100, 500)
  }
}

/**
  **
  *
  *A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
  *
  * Find the largest palindrome made from the product of two 3-digit numbers.
  *
  */
object Problem4 {
  def main(args: Array[String]): Unit = {
    println("the largest palindrome made from the product of two 3-digit numbers")
    println(isPalindrome(12321L))

    // fill in answer here
  }

  def isPalindrome(n:Long): Boolean = {
    if (n < 0) return false // arbitrary decision, negative numbers are not palindromes
    if (n < 10 ) return true;

    val asString = n.toString
    val len = asString.length()
    val maxCheck = len >> 1
    var index = 0
    while (index <= maxCheck) {
      if (asString.charAt(index) != asString.charAt(len - (index + 1))) {
        return false
      }
      index += 1
    }
    return true
  }


  // Naive, unsatisfying implementation but for/yield syntax is kind of cool.
  // This is just fine for the stated problem
  def largestPalindromeProductBruteForceWithYield(from:Int, to:Int):Long = {
    // TODO - this apparently produces a Vector. Investigate what Vectors are in Scala
    val palindromes = for { x <- to.toLong.to(from) by -1; y <- x to from by -1; if (isPalindrome(x*y))}
      yield x*y
    palindromes.sorted.last
  }

  // A slightly less naive, still unsatisfying implementation, using while instead of for/yield
  // This level of optimization is not required for the project euler problem
  def largestPalindromeProductBruteForceWithWhile(from:Int, to:Int):Long = {
    var biggestPalindrome:Long = 0

    var x:Long = to.toLong
    var y:Long = 0L
    while (x >= from) {
      if (biggestPalindrome > x*x) {
        // We can't possibly compute anything bigger than the currently computed value
        return biggestPalindrome
      }
      y = x
      while (y >= from) {
        val product = x*y
        if (isPalindrome(product) && product > biggestPalindrome) {
          biggestPalindrome = product
        }
        y -= 1L
      }
      x -= 1L
    }
    biggestPalindrome
  }

}