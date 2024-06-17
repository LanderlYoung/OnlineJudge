package problem16

import timedTest
import kotlin.math.abs

class Solution {
  fun threeSumClosest(nums: IntArray, target: Int): Int {
    nums.sort()

    var closest = Int.MAX_VALUE
    var i = 2
    while (i < nums.size) {
      // for same values, move to the right most one
      while (i + 1 < nums.size && nums[i] == nums[i + 1]) {
        i++
      }

      // two sum, using two pointer method
      var left = 0
      var right = i - 1
      val newTarget = target - nums[i]

      while (left < right) {
        val sum = nums[left] + nums[right]

        if (sum == newTarget) {
          return target
        }

        if (abs(newTarget - sum) < abs(target - closest)) {
          closest = nums[i] + sum
        }

        if (sum < newTarget) {
          // left++
          val lv = nums[left]
          while (left < right && nums[left] == lv) {
            left++
          }
        } else {
          val rv = nums[right]
          while (right > left && nums[right] == rv) {
            right--
          }
        }
      }

      i++
    }

    return closest
  }
}

// test
fun test(
  // input
  input: IntArray,
  target: Int,

  // expected
  expected: Int,
) {

  val result = Solution().threeSumClosest(input, target)
  println("${input.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
}

fun main() = timedTest {
  test(intArrayOf(0, 3, 97, 102, 200), 300, 300)
  test(intArrayOf(1, 1, 1, 0), 100, 3)
  test(intArrayOf(0, 1, 2), 3, 3)
  test(intArrayOf(-1, 2, 1, -4), 1, 2)
  test(intArrayOf(0, 0, 0), 1, 0)
}