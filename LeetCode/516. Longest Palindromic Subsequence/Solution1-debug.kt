package problem516_1_debug

import shouldBeEqualTo

//
class Solution {
  /**
   * Step 1: define sub problem
   * f(i, j) -> LPS for `s[i .. j]`
   *   condition: 0 <= i < j < s.length
   *   f(i, i) == 1
   *
   * Step 2: define recursion formula
   * f(i, j) ->
   *   if (s[i] == s[j]) -> f(i+1, j-1) + 2
   *   else max(f(i+1,j), f(i, j-1))
   *
   * Step 3: iterate order
   *   i + 1-> i; j -> j-1
   *   so we go i-descent, j-ascend
   */
  fun longestPalindromeSubseq(s: String): Int {
    val dp = Array(s.length) { Array<StringBuilder?>(s.length) { null } }

    var ret: StringBuilder = StringBuilder().append(s[0])
    for (i in s.indices.reversed()) {
      dp[i][i] = StringBuilder().append(s[i])

      for (j in i + 1..<s.length) {
        val newSb = if (s[i] == s[j]) {
          if (i + 1 <= j - 1) {
            StringBuilder(dp[i + 1][j - 1]!!)
          } else {
            StringBuilder()
          }
            .insert(0, s[i])
            .append(s[j])
        } else {
          if (dp[i + 1][j]!!.length > dp[i][j - 1]!!.length) {
            StringBuilder(dp[i + 1][j])
          } else {
            StringBuilder(dp[i][j - 1])
          }
        }
        dp[i][j] = newSb

        if (ret.length < newSb.length) {
          ret = newSb
        }
      }
    }

    println(ret)
    return ret.length
  }
}

fun main() {
  fun test(
    input: String,
    expected: Int,
  ) {
    val result = Solution().longestPalindromeSubseq(input)
    println("$input -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test("a", 1)
  test("ab", 1)
  test("abc", 1)
  test("abca", 3)
  test("cbbd", 2)
  test("c1bb2d", 2)
  test("c1bab2d", 3)
  test("0c61bb25d4", 2)
  test("d0c61bb25d4", 4)
  test("0c61bxb25d4", 3)
  test("bbbab", 4)
  test("1a2b4c6b8a0", 5) // -> abcba
  test("babcababad", 7) // -> bababab
  test("1a23a45a67a89a0", 5) // -> aaaaa, aa4aa
  test("123x454321x", "123454321".length)

  test("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew",
    159)
}