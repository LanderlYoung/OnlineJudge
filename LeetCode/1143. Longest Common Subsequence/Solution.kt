package problem1143

import shouldBeEqualTo
import kotlin.math.max

class Solution {

  /**
   * # 1. define sub-problem
   *   f(i, j) -> LCS of text1[0..<i] & text2[0..<j]
   *   edge: f(i, 0) = 0, f(j, 0) = 0
   *   result: f(text1.length, text2.length)
   *
   * # 2. recursion formula
   * f(i, j) ->
   *  if (text1[i] == text2[j]) {
   *      // [i] == [j], LCS grows longer
   *     f(i - 1, j - 1) + 1
   *   } else {
   *     // [i] != [j]
   *     // text1: xxxxxI
   *     // text2: xxxxxJ
   *     max(f(i - 1, j), f(i, j - 1))
   *   }
   *
   *  # 3. iterate order
   *  i <- i -1, j <- j -1
   *  i ascend, j ascend
   */
  fun longestCommonSubsequence(text1: String, text2: String): Int {
    val dp = Array(text1.length + 1) { IntArray(text2.length + 1) }

    for (i in 1..text1.length) {
      for (j in 1..text2.length) {
        val lcs = if (text1[i - 1] == text2[j - 1]) {
          dp[i - 1][j - 1] + 1
        } else {
          max(dp[i - 1][j], dp[i][j - 1])
        }
        dp[i][j] = lcs
      }
    }

    return dp[text1.length][text2.length]
  }
}

fun main() {
  fun test(
    text1: String, text2: String,
    expected: Int,
  ) {
    val result = Solution().longestCommonSubsequence(text1, text2)
    println("($text1, $text2) -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(text1 = "abcde", text2 = "ace", 3)
  test(text1 = "abc", text2 = "abc", 3)
  test(text1 = "abc", text2 = "def", 0)
}