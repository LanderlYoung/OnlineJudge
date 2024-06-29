package problem474_2

import shouldBeEqualTo

class Solution {
  /**
   * thoughts:
   * 1. it's s knapsack(coin change) problem
   * 2. but not necessarily reach the max value
   * 3. and the max value has two dimensions
   * 4. strs... can't be reused
   */
  fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
    // 1. define sub problem
    // f(si, mi, ni) -> using strs[0..si] to make-up exactly mi,ni OaZ
    // result is: max(f(strs.length - 1, ..., ...))
    val dp = Array(m + 1) { IntArray(n + 1) }

    // build layer by layer
    var maxLen = 0
    for (si in 0..<strs.size) {
      val zeros = strs[si].count { it == '0' }
      val ones = strs[si].length - zeros

      // <<<NOTE HERE>>>: reverse iterate
      for (mi in m downTo 0) {
        for (ni in n downTo 0) {
          // 2. define recursion formula
          // f(si, mi, ni) ->
          val unselected = dp[mi][ni] // 1. f(si - 1, mi, ni) -> not using strs[si] element
          val selected =
            if (mi - zeros >= 0 && ni - ones >= 0) {
              dp[mi - zeros][ni - ones] + 1 // 2. f(si - 1, mi - zeros, ni - zeros) using strs[si] element
              // <<<NOTE HERE>>>: this is why we need a reverse iterate
              // dp[mi - zeros][ni - ones] is not updated yet, so it still represents f(si - 1, ...) layer
            } else {
              0
            }

          dp[mi][ni] = maxOf(unselected, selected)

          maxLen = maxOf(maxLen, dp[mi][ni])
        }
      }
    }

    return maxLen
  }
}

fun main() {
  fun test(
    strs: Array<String>, m: Int, n: Int,
    expected: Int,
  ) {
    val result = Solution().findMaxForm(strs, m, n)
    println("-> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(arrayOf("10", "0001", "111001", "1", "0"), 5, 3, 4)
  test(arrayOf("10", "0", "1"), 1, 1, 2)

}