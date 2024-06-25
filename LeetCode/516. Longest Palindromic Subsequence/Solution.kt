package problem516

import shouldBeEqualTo
import kotlin.math.max

/**
 * refer to Problem. 5
 *
 * ## Algorithm:
 * using spread out method, time complexity O(N^3)
 *
 * ## Result: failed
 * eg: 123x454321x -> 123x321, but should be 123454321
 * left search found left-3 match right-most-3, resulting 3x3
 * actually longest should be 123454321
 *
 */
class Solution {
  fun longestPalindromeSubseq(s: String): Int {
    var ret = 0
    for (i in s.indices) {
      ret = max(ret, spreadOut(s, i, i))
      if (i + 1 < s.length && s[i] == s[i + 1]) {
        ret = max(ret, spreadOut(s, i, i + 1))
      }
    }
    return ret
  }

  // O(N^2)
  private fun spreadOut(s: String, startL: Int, startR: Int): Int {
    val sb: StringBuilder?
    if (debug) {
      sb = StringBuilder()
      sb.append(s[startL])
      if (startL != startR) {
        sb.append(s[startR])
      }
    } else {
      sb = null
    }

    var length = startR - startL + 1
    var left = startL - 1
    var right = startR + 1
    while (left >= 0) {
      // find right char equals left
      val char = s[left]
      var r = right
      while (r < s.length && s[r] != char) r++

      if (r != s.length) {
        // found pair <left, right>
        length += 2
        left--
        right = r + 1

        // debug only
        if (debug) {
          sb!!.insert(0, char)
          sb.append(char)
        }
      } else {
        // not found pair <left, right>
        // do left--, try to find new pair
        left--
      }
    }

    return length
  }

  companion object {
    val debug = true
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

  test("123x454321x", "123454321".length)

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

  // failed test, not debuggable...
  test(
    "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew",
    159
  )
}