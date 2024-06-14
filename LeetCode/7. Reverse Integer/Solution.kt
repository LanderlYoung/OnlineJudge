package problem7

import kotlin.math.absoluteValue

class Solution {
  fun reverse(x: Int): Int {
    val sign = if (x < 0) -1 else 1

    return try {
      sign * x.absoluteValue.toString().reversed().toInt()
    } catch (e: NumberFormatException) {
      0
    }
  }
}

// test

fun test(
  // input
  i: Int,
  // expected
  expected: Int,
) {

  val result = Solution().reverse(i)
  println("$i -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  test(Int.MAX_VALUE, 0)
  test(123, 321)
  test(0, 0)
  test(-1, -1)
  test(-123, -321)
  test(1234567, 7654321)
  test(-1234567, -7654321)
}