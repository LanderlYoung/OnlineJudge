package problem96

import shouldBeEqualTo

class Solution {
  /**
   * # 1. define sub problem
   * f(i) -> the problem
   *
   * # 2. recursion formula
   * ```
   * part-1
   *    \
   *     \
   *     (i)
   *     /
   *    /
   * part-2
   * ```
   *
   * Let `i` be the largest number in tree, split numbers to be part-1 and part-2,
   * so that `i` is the right child of part-1, part-2 is the left child of `i`.
   * According to the instincts of BTS, we have:
   * 1. part-1 < i
   * 2. part-2 < i
   * 3. part-1 < part-2
   * 4. either part-1 or part-2 can be empty
   * 5. count(part-1) + count(part-2) = i - 1
   *
   * `f(i) = sum(f(i - 1 - j) * f(j))`, where `j in [0..i-1]`
   *
   * edge condition: `f(0) -> 1`
   */
  fun numTrees(n: Int): Int {
    val f = IntArray(n + 1)
    f[0] = 1

    for (i in 1..n) {
      var sum = 0
      for (j in 0..i - 1) {
        sum += f[i - 1 - j] * f[j]
      }
      f[i] = sum
    }

    return f[n]
  }
}

fun main() {
  fun test(
    input: Int,
    expected: Int,
  ) {
    val result = Solution().numTrees(input)
    println("$input -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(1, 1)
  test(2, 2)
  test(3, 5)


}