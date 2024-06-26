package problem300

import shouldBeEqualTo

class Solution {
  /**
   *
   * # 1. sub-problem
   *    f(i) -> LIS that ends with s[i]
   *    result: max(f(...))
   *    edge condition:
   *      f(0) -> 1
   *
   * # 2. recursion formula
   *    f(i) ->
   *      for (ix in 0..<i) {
   *        max( nums[ix] < num[i] && f(ix))
   *      }
   *
   * Complexity: O(N^2)
   *
   */
  fun lengthOfLIS(nums: IntArray): Int {
    val dp = IntArray(nums.size)
    dp[0] = 1

    for (i in 1..<nums.size) {
      var len = 1
      for (j in 0..<i) {
        if (nums[j] < nums[i]) {
          len = maxOf(len, dp[j] + 1)
        }
      }

      dp[i] = len
    }

    return dp.max()
  }
}

fun main() {
  fun test(
    vararg input: Int,
    expected: Int,
  ) {
    val result = Solution().lengthOfLIS(input)
    println("${input.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(
    0, 1, 0, 3, 2, 3,
    expected = 4
  )

  test(
    10, 9, 2, 5, 3, 7, 101, 18,
    expected = 4
  )

  test(
    7, 7, 7, 7, 7, 7, 7,
    expected = 1
  )
}