package problem646

import shouldBeEqualTo

class Solution {
  fun findLongestChain(pairs: Array<IntArray>): Int {
    pairs.sortBy { it.first() }

    val dp = IntArray(pairs.size)
    dp[0] = 1

    var m = 1
    for (i in 1..<pairs.size) {
      var maxLen = 1

      for (j in 0..<i) {
        if (pairs[j][1] < pairs[i][0]) {
          maxLen = maxOf(maxLen, dp[j] + 1)
        }
      }

      dp[i] = maxLen
      m = maxOf(m, maxLen)
    }

    return m
  }
}

fun main() {
  fun test(
    pairs: Array<Pair<Int, Int>>,
    expected: Int,
  ) {
    val result = Solution().findLongestChain(pairs.map { intArrayOf(it.first, it.second) }.toTypedArray())
    println("${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(arrayOf(1 to 2, 2 to 3, 3 to 4), 2)
  test(arrayOf(1 to 2, 7 to 8, 4 to 5), 3)
}