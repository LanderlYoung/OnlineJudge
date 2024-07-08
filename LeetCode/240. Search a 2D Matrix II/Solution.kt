package problem240

import matrix2d
import shouldBeEqualTo

// note: there is better solution Z-scan
class Solution {
  // binary search, line by line
  fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    if (target < matrix[0][0] || target > matrix[matrix.size - 1][matrix[0].size - 1]) {
      return false
    }

    for (row in matrix.indices) {
      // binary search
      if (matrix[row].binarySearch(target) >= 0) {
        return true
      }
    }
    return false
  }
}
// var left = 0
// var right = matrix[0].size - 1
// while (left <= right) {
//   val mid = (left + right) / 2
//   if (target < matrix[row][mid]) {
//     right = mid - 1
//   } else if (target > matrix[row][mid]) {
//     left = mid + 1
//   } else {
//     return true
//   }
// }

fun main() {
  fun test(
    matrix: String, target: Int,
    expected: Boolean,
  ) {
    val result = Solution().searchMatrix(matrix2d(matrix), target)
    println("$target -> $result == $expected -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test("[[1,3,5,7,9],[2,4,6,8,10],[11,13,15,17,19],[12,14,16,18,20],[21,22,23,24,25]]", 13, true)
  test("[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]", 15, true)
  test("[[1,3,5,7,9],[2,4,6,8,10],[11,13,15,17,19],[12,14,16,18,20],[21,22,23,24,25]]", 13, true)
  test("[[1]]", 1, true)
  test("[[1,1]]", 1, true)
  test("[[-1,3]]", 3, true)
  test("[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]", 5, true)
  test("[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]", 20, false)
}