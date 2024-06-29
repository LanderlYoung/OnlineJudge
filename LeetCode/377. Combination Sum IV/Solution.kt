package problem377

import shouldBeEqualTo

// thanks to https://leetcode.cn/problems/combination-sum-iv/solutions/2706336/ben-zhi-shi-pa-lou-ti-cong-ji-yi-hua-sou-y52j/?envType=study-plan-v2&envId=dynamic-programming
// 1. this is actually more like Climb Ladder than Coin Change
// 2. this is more a permutation problem than a combination one
// 3. just like climb ladder, how to reach target by climb any of [nums..] steps each time
class Solution {
  fun combinationSum4(nums: IntArray, target: Int): Int {
    val dp = IntArray(target + 1)
    dp[0] = 1

    for (i in 1..target) {
      var sum = 0
      for (num in nums) {
        if (i - num >= 0) {
          sum += dp[i - num]
        }
      }
      dp[i] = sum
    }

    return dp[target]
  }
}

fun main() {
  fun test(
    vararg nums: Int,
    target: Int,
    expected: Int,
  ) {
    val result = Solution().combinationSum4(nums, target)
    println("${nums.contentToString()} ${target} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(9, target = 3, expected = 0)
  test(1, 2, 3, target = 4, expected = 7)
}