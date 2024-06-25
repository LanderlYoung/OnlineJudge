package problem115

import shouldBeEqualTo

class Solution {
  /**
   * idea: permutation + dp
   *
   * # 1. define sub-problem
   * f(i, j) -> use s[i-1] as the tail letter && s[0..i], t[0..j] DS count
   * result: sum(f(0..<s.length, t.length - 1))
   * edge condition:
   *  * f(i, 0) -> if (s[i] == t[0]) 1 else 0
   * constraints: i > =j (if i < j, dp[s][j] must be 0)
   *
   * # 2. recursion formula
   * f(i, j) -> if ([i] == [j]) {
   *   use s[i] as the end point, see how many DS can be made up for t[0..j]
   *   so we se how many t[0..j-1] is match in s[0..i-1]
   *
   *   sum(f(0..i-1, j-1)
   * } else {
   *  0
   * }
   */
  fun numDistinct(s: String, t: String): Int {
    val dp = Array(s.length) { IntArray(t.length) }

    // j == 0
    for (i in s.indices) {
      dp[i][0] = if (s[i] == t[0]) 1 else 0
    }

    for (i in s.indices) {
      for (j in 1..<t.length) {
        dp[i][j] =
          if (s[i] != t[j]) {
            0
          } else {
            var dsSum = 0
            for (ix in 0..<i) {
              dsSum += dp[ix][j - 1]
            }
            dsSum
          }
      }
    }

    var sum = 0
    for (i in s.indices) {
      sum += dp[i][t.length - 1]
    }
    return sum
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