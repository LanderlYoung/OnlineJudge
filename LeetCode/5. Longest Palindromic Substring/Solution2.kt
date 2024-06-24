package problem5_2

// DP
class Solution {
  /**
   * # 1. define the problem -- what is a palindrome
   * ## case 1: odd length
   * - abc
   * - 12321
   * ## case 2: even length
   * - aa
   * - abba
   * - aaaa
   *
   * # 2. dp method
   * ## Step 1. define Sub Problem
   *   f(i, j) -> s[i, j] is a palindrome or not (i <= j)
   *
   * ## Step 2. define Recursion Formula
   *   f(i, j) -> f(i + 1, j - 1) && s[i] == s[j]
   *   note:
   *     1. `i == j` is allowed, careful about edge condition
   *     2. `i + 1  == j` is allowed, also care about edge condition
   *     3. cond: (i + 1 > j - 1 >= 0) && f(i + 1, j - 1)
   *
   * ## Step 3. define Iterate Order
   * notice the Recursion Formula need (i + 1, j - 1)
   * So, we go i-descending j-ascending order
   */
  fun longestPalindrome(s: String): String {
    val dp = Array(s.length) { i ->
      BooleanArray(s.length)
    }

    var maxLength = 0
    var maxI = 0
    var maxJ = 0

    for (i in s.indices.reversed()) {
      for (j in i..<s.length) {
        val validRange = i + 1 < j - 1
        val subStringIsPalindrome = !validRange || dp[i + 1][j - 1]

        val isPalindrome = subStringIsPalindrome && s[i] == s[j]
        dp[i][j] = isPalindrome

        if (isPalindrome && j - i + 1 > maxLength) {
          maxLength = j - i + 1
          maxI = i
          maxJ = j
        }
      }
    }

    return s.substring(maxI, maxJ + 1)
  }
}

fun main() {
  fun test(
    input: String,
    expected: String,
  ) {
    val result = Solution().longestPalindrome(input)
    println("$input -> ${result} == ${expected} -> ${result == expected}")
  }

  test("babad", "aba")
  test("a", "a")
  test("aa", "aa")
  test("aba", "aba")
  test("abcba", "abcba")
  test("abba", "abba")
  test("cbbd", "bb")
}