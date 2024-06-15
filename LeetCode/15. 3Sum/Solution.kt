package problem15

import kotlin.time.measureTime

// brutal force
// O(N^3) -- Time Limit Exceeded
class Solution {
  fun threeSum(nums: IntArray): List<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    for (i in nums.indices) {
      for (j in (i + 1)..<nums.size) {
        for (k in (j + 1)..<nums.size) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            result += listOf(nums[i], nums[j], nums[k]).sorted()
          }
        }
      }
    }

    return result.toList()
  }
}

// test
fun test(
  // input
  input: IntArray,

  // expected
  expected: List<List<Int>>,
) {

  val result = Solution().threeSum(input)
  val eq = result.size == expected.size && result.toSet() == expected.toSet()
  println("${input.contentToString()} -> ${result} == ${expected} -> ${eq}")
}

fun main() {
  println("${
    measureTime {
      test(
        intArrayOf(-1, 0, 1, 2, -1, -4),
        listOf(
          listOf(-1, -1, 2),
          listOf(-1, 0, 1)
        )
      )
    }
  }ms")
}