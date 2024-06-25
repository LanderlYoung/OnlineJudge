package problem712

import shouldBeEqualTo

class Solution {
  /**
   * # 1. define sub-problem
   * f(i, j) -> s1[0..<i] s2[0..<j] DS
   * result: f(s1.length, s2.length)
   * edge condition: f(i, 0) -> sum([0..<i]..); f(0, j) -> sum([0..<j]..)
   *
   * # 2. recursion formula
   * f(i, j) -> if ([i - 1] == [j - 1]) {
   *   f(i - 1, j - 1)
   * } else {
   *    minOf(
   *      f(i - 1, j) + [j-1], // delete i-1
   *      f(i, j - 1) + [i - 1], // delete j-1
   *      f(i -1, j - 1) + [i - 1] + [j - 1] // delete i-1&&j-1
   *    )
   * }
   */
  fun minimumDeleteSum(s1: String, s2: String): Int {
    val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

    var sum = 0
    for (i in s1.indices) {
      sum += s1[i].code
      dp[i + 1][0] = sum
    }
    sum = 0
    for (j in s2.indices) {
      sum += s2[j].code
      dp[0][j + 1] = sum
    }

    for (i in 1..s1.length) {
      for (j in 1..s2.length) {
        dp[i][j] =
          if (s1[i - 1] == s2[j - 1]) {
            dp[i - 1][j - 1]
          } else {
            minOf(
              dp[i - 1][j] + s1[i - 1].code,
              dp[i][j - 1] + s2[j - 1].code,
              dp[i - 1][j - 1] + s1[i - 1].code + s2[j - 1].code
            )
          }
      }
    }
    return dp[s1.length][s2.length]
  }
}

fun main() {
  fun test(
    s1: String, s2: String,
    expected: Int,
  ) {
    val result = Solution().minimumDeleteSum(s1, s2)
    println("($s1, $s2) -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(s1 = "sea", s2 = "eat", 231)
  test(s1 = "delete", s2 = "leet", 403)
}