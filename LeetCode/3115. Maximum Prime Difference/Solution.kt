package problem3115

import matrix1d
import shouldBeEqualTo

class Solution {
  fun maximumPrimeDifference(nums: IntArray): Int {
    var left = 0
    while (left < nums.size && !prim.contains(nums[left])) left++

    var right = nums.size - 1
    while (right >= 0 && !prim.contains(nums[right])) right--

    return right - left
  }

  companion object {
    private val prim = hashSetOf(
      2, 3, 5, 7, 11,
      13, 17, 19, 23, 29,
      31, 37, 41, 43, 47,
      53, 59, 61, 67, 71,
      73, 79, 83, 89, 97
    )
  }
}

fun main() {
  fun test(
    nums: IntArray,
    expected: Int,
  ) {
    val result = Solution().maximumPrimeDifference(nums)
    println("${nums.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(matrix1d(" [4,2,9,5,3] "), 3)
  test(matrix1d("[4,8,2,8] "), 0)
}