package problem115_2

import shouldBeEqualTo

class Solution {
  /**
   * idea: permutation + dp
   *
   * # 1. define sub-problem
   * f(i, j) -> s[0..<i], t[0..<j] DS count (s[i - 1] maybe used, maybe not)
   * result: f(s.length, t.length)
   * edge condition:
   *  * f(i, 0) -> ? // don't make sense?
   *  * f(i, 1) ->
   *  * f(0, j) -> 0 // s
   * constraints: i > =j (if i < j, dp[s][j] must be 0)
   *
   * # 2. recursion formula
   * f(i, j) -> if ([i - 1] == [j - 1]) {
   *   f(i-1, j-1) +  // use s[i - 1] as end point
   *   f(i-1, j)      // don't use s[i - 1] as end point
   * } else {
   *  f(i-1, j)       // can't use s[i - 1] as end point
   * }
   */
  fun numDistinct(s: String, t: String): Int {
    val dp = Array(s.length + 1) { IntArray(t.length + 1) }

    // j == 1
    var matched = 0
    for (i in 1..s.length) {
      if (s[i - 1] == t[0]) {
        matched++
      }
      dp[i][1] = matched
    }

    for (i in 1..s.length) {
      for (j in 2..t.length) {
        dp[i][j] =
          if (s[i - 1] == t[j - 1]) {
            dp[i - 1][j - 1] + dp[i - 1][j]
          } else {
            dp[i - 1][j]
          }
      }
    }

    return dp[s.length][t.length]
  }
}

fun main() {
  fun test(
    s: String, t: String,
    expected: Int,
  ) {
    val result = Solution().numDistinct(s, t)
    println("($s, $t) -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(s = "rrabab", t = "rab", 6)
  test(s = "rraabb", t = "rab", 8)
  test(s = "rabbbit", t = "rabbit", 3)
  test(s = "babgbag", t = "bag", 5)
}