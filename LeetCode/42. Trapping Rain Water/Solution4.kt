package problem42_4

// 单调栈
class Solution {

  fun trap(height: IntArray): Int {
    val stack = IntArray(height.size)
    var sp = -1

    var result = 0

    repeat(height.size) { index ->
      val h = height[index]

      while (sp >= 0 && h >= height[stack[sp]]) {
        // the wall goes higher, means there is trapped water inside
        if (sp - 1 >= 0) {
          val width = index - stack[sp - 1] - 1
          val trappedHeightSegment = kotlin.math.min(h, height[stack[sp - 1]]) - height[stack[sp]]
          result += width * trappedHeightSegment
        }

        // pop it
        stack[sp] = 0 // clear for debug
        sp--
      }

      stack[++sp] = index
    }

    return result
  }

}

fun main() {
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


  run {
    val arr = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
    val ret = Solution().trap(arr)
    println("$ret == 6")
  }
}