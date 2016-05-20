package dan.problem1

/**

If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.

  */

object Problem1 {

  /**
    * Compute sum of numbers from 1 to n
    */
  def sumFromOneToN(n:Int):Long = {
    // Note to self: investigate performance ramifications of assert/assume/require
    assert(n >= 0)
    val sum:Long = n.toLong * (n+1) /2
    sum
  }

  def main(args: Array[String]): Unit = {
    println("Multiples of 3 and 5 below 1000 add to")
    val n:Int = 999;
    val sumOf3s = sumFromOneToN(n/3) * 3
    val sumOf5s = sumFromOneToN(n/5) * 5
    val sumOf15s = sumFromOneToN(n/15) * 15;
    println(sumOf3s + sumOf5s - sumOf15s)
  }
}