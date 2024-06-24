package problem5

// spread out
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
   * # 2. inside out spread
   * search from one-point to two directions (left, right)
   *
   */
  fun longestPalindrome(s: String): String {
    for (i in s.indices) {
      spreadOut(s, i, i)
      spreadOut(s, i, i + 1)
    }

    return longestString.toString()
  }

  private var longestLength: Int = 0
  private var longestString: CharSequence = ""

  private fun spreadOut(s: String, l: Int, r: Int) {
    var left = l
    var right = r
    while (left >= 0 && right < s.length && s[left] == s[right]) {
      left--
      right++
    }
    // left, right -- both exclusive

    val length = right - (left + 1)
    if (length > longestLength) {
      longestLength = length
      longestString = s.subSequence(left + 1, right)
    }
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

  test("a", "a")
  test("aa", "aa")
  test("aba", "aba")
  test("abcba", "abcba")
  test("abba", "abba")
}