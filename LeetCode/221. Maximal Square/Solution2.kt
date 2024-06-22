package problem221_2

import kotlin.math.max
import kotlin.math.min

class Solution {
  /**
   * step 1: define sub-problem
   *   f(i, j) == H*W -> means when we use f(i, j) as right-bottom corner, this is a W*H sized Rectangle contains only 1
   *   so the final answer should be max(min(f(i,j).H, f(i,j).H) ^ 2)
   *
   * step 2: define recursion formula
   *   f(i, j) = if(matrix[i][j] == '0') [0, 0] else {
   *          top = f(i - 1, j)
   *          left = f(i, j - 1)
   *          w = min(top.w, left.w + 1)
   *          h = min(top.h + 1, left.h)
   *       else (0, 0)
   *   }
   *
   *
   */
  fun maximalSquare(matrix: Array<CharArray>): Int {
    val dp = Array(matrix.size) { IntArray(matrix[0].size) }

    fun isAll1(ltX: Int, ltY: Int, rbX: Int, rbY: Int): Boolean {
      try {
        for (x in ltX..rbX) {
          for (y in ltY..rbY) {
            if (matrix[x][y] != '1') {
              return false
            }
          }
        }
      } catch (e: ArrayIndexOutOfBoundsException) {
        e.printStackTrace()
      }
      return true
    }

    for (i in matrix.indices) {
      for (j in matrix[i].indices) {
        if (matrix[i][j] != '1') {
          dp[i][j] = 0
        } else {
          val top = dp.getOrNull(i - 1)?.getOrNull(j) ?: 0
          val left = dp.getOrNull(i)?.getOrNull(j - 1) ?: 0

          val s1 = if (
            isAll1(i - top, j - top, i, j - top) &&
            isAll1(i, j - top, i, j)
          ) {
            (top + 1) * (top + 1)
          } else {
            0
          }

          val s2 = if (
            isAll1(i - left, j - left, i - left, j) &&
            isAll1(i, j - left, i, j)
          ) {
            (left + 1) * (left + 1)
          } else {
            0
          }

          dp[i][j] = max(s1, s2)
        }
      }
    }

    return 0
  }
}

fun main() {
  // test
  fun test(
    // input
    matrix: Array<String>,

    // expected
    expected: Int,
  ) {

    val result = Solution().maximalSquare(matrix.map { it.toCharArray() }.toTypedArray())
    println("${matrix.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
  }

  test(
    arrayOf(
      "10100",
      "10111",
      "11111",
      "10010"
    ),
    4
  )

  test(
    arrayOf(
      "01",
      "10"
    ),
    1
  )
}