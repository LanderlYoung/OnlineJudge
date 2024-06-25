package problem72

import shouldBeEqualTo

class Solution {
  /**
   * # 1. define sub problem
   *   f(i, j) -> word1[0..<i] word2[0..<j] edit distance
   *   result: f(word1.length, word2.lengtg)
   *   edge condition: f(i, 0) -> i; f(0, j) -> j
   *
   * # 2. recursion formula
   * f(i, j) -> if ([i - 1] == [j - 1]) {
   *   f(i-1, j-1)
   * } else {
   *  // min prev + delete/insert one
   *  min(f(i-1,j) + 1, f(i, j-1) + 1, f(i-1, j-1) + 1)
   * }
   *
   */
  fun minDistance(word1: String, word2: String): Int {
    val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }

    for (i in 0..word1.length) {
      dp[i][0] = i
    }
    for (j in 0..word2.length) {
      dp[0][j] = j
    }

    for (i in 1..word1.length) {
      for (j in 1..word2.length) {
        dp[i][j] =
          if (word1[i - 1] == word2[j - 1]) {
            dp[i - 1][j - 1]
          } else {
            minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
          }
      }
    }

    return dp[word1.length][word2.length]
  }
}

fun main() {
  fun test(
    word1: String, word2: String,
    expected: Int,
  ) {
    val result = Solution().minDistance(word1, word2)
    println("($word1, $word2) -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test("horse", "ros", 3)
  test("intention", "execution", 5)
  test("", "a", 1)
}