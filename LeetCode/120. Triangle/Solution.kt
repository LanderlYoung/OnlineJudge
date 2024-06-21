package problem120

import kotlin.math.min

class Solution {
  /**
   * recursion formula:
   * f(row, i) -> minimum path sum to i, j
   *
   * f(row, i) =  min(f(row-1, i-1), f(row-1, i)) + triangle[row][i]
   *
   * result = min(f(lastRow, 0..n))
   */
  fun minimumTotal(triangle: List<List<Int>>): Int {
    val minSum = IntArray(triangle.last().size)

    for (row in triangle.indices) {
      // from right to left
      for (i in triangle[row].indices.reversed()) {
        if (row == 0) {
          minSum[i] = triangle[row][i]
        } else {
          minSum[i] =
            min(
              if (i == triangle[row].size - 1) Int.MAX_VALUE else minSum[i],
              if (i != 0) minSum[i - 1] else Int.MAX_VALUE
            ) + triangle[row][i]
        }
      }
    }

    return minSum.min()
  }
}

fun main() {
  // test
  fun test(
    // input
    triangle: List<List<Int>>,

    // expected
    expected: Int,
  ) {

    val result = Solution().minimumTotal(triangle)
    println("${result} == ${expected} -> ${result == expected}")
  }

  // [[2],[3,4],[6,5,7],[4,1,8,3]]
  test(
    listOf(
      listOf(2),
      listOf(3, 4),
      listOf(6, 5, 7),
      listOf(4, 1, 8, 3)
    ),
    11
  )

  test(
    listOf(
      listOf(-10),
    ),
    -10
  )
}