package problem673

import shouldBeEqualTo

class Solution {
  fun findNumberOfLIS(nums: IntArray): Int {
    // <length, count>
    val dp = Array<Pair<Int, Int>?>(nums.size) { null }
    dp[0] = 1 to 1

    var maxLen = 1
    for (i in 1..<nums.size) {
      var len = 1
      for (j in 0..<i) {
        if (nums[j] < nums[i]) {
          len = maxOf(len, dp[j]!!.first + 1)
        }
      }

      var count = 0
      if (len == 1) {
        count = 1
      } else {
        for (j in 0..<i) {
          if (nums[j] < nums[i] && dp[j]!!.first == len - 1) {
            count += dp[j]!!.second
          }
        }
      }

      dp[i] = len to count

      if (maxLen < len) {
        maxLen = len
      }
    }

    var count = 0
    for (i in nums.indices) {
      if (dp[i]!!.first == maxLen) {
        count += dp[i]!!.second
      }
    }

    return count
  }
}

fun main() {
  fun test(
    vararg nums: Int,
    expected: Int,
  ) {
    val result = Solution().findNumberOfLIS(nums)
    println("${nums.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(1, expected = 1)
  test(1, 3, 5, 4, 7, expected = 2)
  test(2, 2, 2, 2, 2, expected = 5)
}