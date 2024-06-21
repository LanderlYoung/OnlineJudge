package problem931

// similar to Triangle
class Solution {
  /**
   * step 1: define sub-problem
   *  f(i, j) means the minimum sum from top to [i, j]
   *  So the answer should be min(f(lastRow, 0..n)...)
   *
   * step 2: recursion formular
   *   f(i, j) = min(f(i-1, j-1) , f(i-1, j), f(i-1, j+1)) + matrix[i, j]
   *
   */
  fun minFallingPathSum(matrix: Array<IntArray>): Int {
    var prevSum = IntArray(matrix[0].size)
    var currentSum = IntArray(matrix[0].size)

    for (row in matrix.indices) {
      for (i in matrix[row].indices) {
        if (row == 0) {
          currentSum[i] = matrix[0][i]
        } else {
          currentSum[i] =
            matrix[row][i] + prevSum[i]
              .coerceAtMost(if (i - 1 >= 0) prevSum[i - 1] else Int.MAX_VALUE)
              .coerceAtMost(if (i + 1 < matrix[row].size) prevSum[i + 1] else Int.MAX_VALUE)
        }
      }
      // swap
      val tmp = prevSum
      prevSum = currentSum
      currentSum = tmp
    }

    return prevSum.min()
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
    val result = Solution().minFallingPathSum(input.clone())
    println("${result} == ${expected} -> ${result == expected}")
  }

  // [[2,1,3],[6,5,4],[7,8,9]]
  test(
    arrayOf(
      intArrayOf(2, 1, 3),
      intArrayOf(6, 5, 4),
      intArrayOf(7, 8, 9)
    ),
    13
  )

  test(
    arrayOf(
      intArrayOf(-19, 57),
      intArrayOf(-40, -5)
    ),
    -59
  )
}