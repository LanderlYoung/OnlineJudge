package problem53

import shouldBeEqualTo

class Solution {
  fun maxSubArray(nums: IntArray): Int {
    // 1. define sub-problem
    // f(i) -> used [i] as end point, the max sub of [..i]
    val dp = IntArray(nums.size)
    dp[0] = nums[0]

    var max = nums[0]
    for (i in 1..<nums.size) {
      // 2. define recursion formula
      // f(i) -> max([i], f(i-1) + [i]))
      dp[i] = maxOf(nums[i], dp[i - 1] + nums[i])

      max = maxOf(max, dp[i])
    }

    return max
  }
}

fun main() {
  fun test(
    vararg nums: Int,
    expected: Int,
  ) {
    val result = Solution().maxSubArray(nums)
    println("${nums.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }
  test(1, expected = 1)
  test(-2, 1, -3, 4, -1, 2, 1, -5, 4, expected = 6)
  test(5, 4, -1, 7, 8, expected = 23)
}