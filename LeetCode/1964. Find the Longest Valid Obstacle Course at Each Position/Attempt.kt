package problem1964

import shouldBeEqualTo

// same as 300.
// TLE
class Solution {
  fun longestObstacleCourseAtEachPosition(obstacles: IntArray): IntArray {
    val dp = IntArray(obstacles.size)
    dp[0] = 1

    for (i in 1..<obstacles.size) {
      var len = 1
      for (j in 0..<i) {
        if (obstacles[j] <= obstacles[i]) {
          len = maxOf(len, dp[j] + 1)
        }
      }

      dp[i] = len
    }

    return dp
  }
}

fun main() {
  fun test(
    input: IntArray,
    expected: IntArray,
  ) {
    val result = Solution().longestObstacleCourseAtEachPosition(input)
    println("${input.contentToString()} -> ${result.contentToString()} == ${expected.contentToString()} -> ${
      result.contentEquals
        (expected)
    }")
    (result contentEquals expected) shouldBeEqualTo true
  }

  test(
    intArrayOf(1, 2, 3, 2),
    intArrayOf(1, 2, 3, 3)
  )

  test(
    intArrayOf(2, 2, 1),
    intArrayOf(1, 2, 1)
  )

  test(
    intArrayOf(3, 1, 5, 6, 4, 2),
    intArrayOf(1, 1, 2, 3, 2, 2)
  )

}