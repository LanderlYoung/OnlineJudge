package problem63

class Solution {
  /**
   * recursion formula:
   *  f(i, j) =
   *    if (obsticalGrid(i, j) == 1) 0
   *    else {
   *      f(i - 1, j) + f(i, j - 1)
   *    }
   */
  fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
    if (obstacleGrid[0][0] == 1) return 0

    val m = obstacleGrid.size
    val n = obstacleGrid[0].size
    val up = Array(m) { IntArray(n) }

    for (i in 0..<m) {
      for (j in 0..<n) {
        if (i == 0 && j == 0) {
          up[0][0] = 1
        } else if (obstacleGrid[i][j] == 1) {
          up[i][j] = 0
        } else {
          up[i][j] =
            (if (i - 1 >= 0) up[i - 1][j] else 0) +
                (if (j - 1 >= 0) up[i][j - 1] else 0)
        }
      }
    }

    return up[m - 1][n - 1]
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

    val result = Solution().uniquePathsWithObstacles(input)
    println("${result} == ${expected} -> ${result == expected}")
  }

  test(
    arrayOf(
      intArrayOf(0, 0, 0),
      intArrayOf(0, 1, 0),
      intArrayOf(0, 0, 0),
    ),
    2
  )

  test(
    arrayOf(
      intArrayOf(0, 1),
      intArrayOf(0, 0),
    ),
    1
  )
}