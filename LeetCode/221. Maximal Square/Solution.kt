package problem221

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
    val dp = Array(matrix.size) {
      Array<Pair<Int, Int>?>(matrix[0].size) { null }
    }

    for (i in matrix.indices) {
      for (j in matrix[i].indices) {
        if (matrix[i][j] != '1') {
          dp[i][j] = 0 to 0
        } else {
          val top = dp.getOrNull(i - 1)?.getOrNull(j) ?: (0 to 0)
          val left = dp.getOrNull(i)?.getOrNull(j - 1) ?: (0 to 0)

          val width =
            if (top.first == 0) {
              left.first + 1
            } else {
              min(top.first, left.first + 1)
            }

          val height =
            if (left.second == 0) {
              top.second + 1
            } else {
              min(top.second + 1, left.second)
            }

          dp[i][j] = width to height
        }
      }
    }

    var maxArea = Int.MIN_VALUE
    for (i in dp.indices) {
      for (j in dp[i].indices) {
        val (w, h) = dp[i][j]!!
        val squareArea = min(w, h) * min(w, h)

        maxArea = max(maxArea, squareArea)
      }
    }

    return maxArea
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