package problem354

import shouldBeEqualTo

class Solution {
  /**
   * similar to 300. Longest Increasing Subsequence
   *
   * # 1. prepare
   *
   * # 2. sub problem
   * f(i) -> Max RDE that must ends with [i]
   * result -> max(f(...))
   *
   * # 3. recursion formula
   *
   * Complexity O(N^2) -> TLE by kotlin
   */
  fun maxEnvelopes(envelopes: Array<IntArray>): Int {
    // 1. prepare
    envelopes.sortBy { it[0] }

    // 2. sub-problem
    val dp = IntArray(envelopes.size)
    val max = IntArray(envelopes.size)

    // edge condition
    dp[0] = 1
    max[0] = 1

    // 3. recursion formula
    var result = 1
    for (i in 1..<envelopes.size) {
      val w = envelopes[i][0]
      val h = envelopes[i][1]

      var maxLen = 1
      for (j in (0..<i).reversed()) {
        val canFit = envelopes[j][0] < w && envelopes[j][1] < h
        if (canFit) {
          maxLen = maxOf(maxLen, dp[j] + 1)
        }
        // can't be bigger, short-cut
        if (maxLen >= max[i - 1] + 1) {
          break
        }
      }
      dp[i] = maxLen
      max[i] = maxOf(max[i - 1], maxLen)

      result = maxOf(result, maxLen)
    }

    return result
  }
}

fun main() {
  fun test(
    vararg envelopes: Pair<Int, Int>,
    expected: Int,
  ) {
    val result = Solution().maxEnvelopes(envelopes.map { intArrayOf(it.first, it.second) }.toTypedArray())
    println("${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  // test(5 to 4, 6 to 4, 6 to 7, 2 to 3, expected = 3)
  // test(1 to 1, 1 to 1, 1 to 1, expected = 1)
  // test(1 to 3, 3 to 5, 6 to 7, 6 to 8, 8 to 4, 9 to 5, expected = 3)
  test(15 to 8, 2 to 20, 2 to 14, 4 to 17, 8 to 19, 8 to 9, 5 to 7, 11 to 19, 8 to 11, 13 to 11, 2 to 13,
    11 to 19, 8 to 11, 13 to 11, 2 to 13, 11 to 19, 16 to 1, 18 to 13, 14 to 17, 18 to 19,
    expected = 5)
}