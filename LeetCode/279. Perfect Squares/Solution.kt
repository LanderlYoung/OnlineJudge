package problem279

import shouldBeEqualTo

class Solution {
  companion object {
    val dp = IntArray(1E4.toInt() + 1) { -1 }.also {
      it[0] = 0
      it[1] = 1
      it[2] = 2
      it[3] = 3
      it[4] = 1
    }
  }

  fun numSquares(n: Int): Int {
    if (n == 0) return 0
    for (i in 1..n) {
      if (dp[i] == -1) {
        var minCount = Int.MAX_VALUE

        var x = 1
        while (x * x <= i) {
          minCount = minOf(minCount, dp[i - x * x] + 1)
          x++
        }
        dp[i] = minCount
      }
    }

    return dp[n]
  }
}

fun main() {
  fun test(
    input: Int,
    expected: Int,
  ) {
    val result = Solution().numSquares(input)
    println("$input -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(9, 1)
  test(12, 3)
  test(13, 2)

}