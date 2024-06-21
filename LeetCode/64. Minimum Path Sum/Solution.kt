package problem64

import kotlin.math.min

class Solution {
  /**
   * recursion formula:
   *     f(i, j) = min(f(i - 1 , j), f(i, j - 1) + grid(i, j)
   */
  fun minPathSum(grid: Array<IntArray>): Int {
    val m = grid.size
    val n = grid[0].size

    val sum = Array(m) { IntArray(n) }

    for (i in 0..<m) {
      for (j in 0..<n) {
        if (i == 0 && j == 0) {
          sum[i][j] = grid[0][0]
        } else {
          val up = if (j >= 1) sum[i][j - 1] else Int.MAX_VALUE
          val left = if (i >= 1) sum[i - 1][j] else Int.MAX_VALUE

          sum[i][j] = min(up, left) + grid[i][j]
        }
      }
    }

    return sum[m - 1][n - 1]
  }
}

fun main() {
  // test
  fun test(
    // input
    input: Array<IntArray>,

    // expected
    expected: Int,
  ) {

    val result = Solution().minPathSum(input)
    println("${result} == ${expected} -> ${result == expected}")
  }

  test(
    arrayOf(
      intArrayOf(1, 3, 1),
      intArrayOf(1, 5, 1),
      intArrayOf(4, 2, 1)
    ),
    7
  )

  test(
    arrayOf(
      intArrayOf(1, 2, 3),
      intArrayOf(4, 5, 6),
    ),
    12
  )
}