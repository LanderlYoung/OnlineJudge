package problem221

import kotlin.math.max
import kotlin.math.min

// failed because sub-problem in not clarified enough
class Solution {
  /**
   * step 1: define sub-problem
   *   f(i, j) == H*W -> means when we use f(i, j) as right-bottom corner, this is a W*H sized Rectangle contains only 1
   *   so the final answer should be max(min(f(i,j).H, f(i,j).H) ^ 2)
   *   -- f(i, j) may be made up to 3 kind of rectangles, which failed to express in a single table
   *
   * step 2: define recursion formula
   *   f(i, j) = if(matrix[i][j] == '0') [0, 0] else {
   *          top = f(i - 1, j)
   *          left = f(i, j - 1)
   *          w = min(top.w, left.w + 1)
   *          h = min(top.h + 1, left.h)
   *       else (0, 0)
   *   }
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
            if (top.width == 0) {
              left.width + 1
            } else {
              min(top.width, left.width + 1)
            }

          val height =
            if (left.height == 0) {
              top.height + 1
            } else {
              min(top.height + 1, left.height)
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
  private val Pair<Int,Int>.width:Int get() = first
  private val Pair<Int,Int>.height:Int get() = first
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