package problem3115_2

import matrix1d
import shouldBeEqualTo

class Solution {
  fun maximumPrimeDifference(nums: IntArray): Int {
    var left = 0
    while (left < nums.size && !prim[nums[left]]) left++

    var right = nums.size - 1
    while (right >= 0 && !prim[nums[right]]) right--

    return right - left
  }

  companion object {
    private val prim = eratosthenes(100)

    // https://zh.wikipedia.org/wiki/%E5%9F%83%E6%8B%89%E6%89%98%E6%96%AF%E7%89%B9%E5%B0%BC%E7%AD%9B%E6%B3%95
    private fun eratosthenes(upperBound: Int): BooleanArray {
      val mark = BooleanArray(upperBound + 1) { true }
      mark[0] = false
      mark[1] = false

      var i = 2
      while (i * i <= upperBound) {
        var j = i * i
        if (mark[i]) {
          while (j <= upperBound) {
            mark[j] = false
            j += i
          }
        }
        i++
      }
      return mark
    }
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