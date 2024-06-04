package problem42_2

import kotlin.math.max
import kotlin.math.min

class Solution {
  fun trap(height: IntArray): Int {
    // helps with speed
    if (height.isEmpty()) return 0;

    var leftMax = 0
    var rightMax = 0

    var left = 0
    var right = height.size - 1

    var result = 0

    while (left < right) {
      leftMax = max(leftMax, height[left])
      rightMax = max(rightMax, height[right])

      if (leftMax < rightMax) {
        // move left forward
        result += leftMax - height[left]
        left++
      } else {
        // move left forward
        result += rightMax - height[right]
        right--
      }
    }

    return result
  }

}

run {
  val arr = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
  val ret = Solution().trap(arr)
  println("$ret == 6")
}

run {
  val arr = intArrayOf(4, 2, 0, 3, 2, 5)
  val ret = Solution().trap(arr)
  println("$ret == 9")
}

run {
  val arr = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
  val ret = Solution().trap(arr)
  println("$ret == 6")
}