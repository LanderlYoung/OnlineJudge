package problem42_2

import kotlin.math.min

class Solution {
  fun trap(height: IntArray): Int {
    val hL = IntArray(height.size)
    val hR = IntArray(height.size)

    var maxL = 0
    for (i in height.indices) {
      maxL = maxL.coerceAtLeast(height[i])
      hL[i] = maxL
    }

    var maxR = 0
    for (i in height.indices.reversed()) {
      maxR = maxR.coerceAtLeast(height[i])
      hR[i] = maxR
    }

    var total = 0
    repeat(height.size) {i ->
      total += min(hL[i], hR[i]) - height[i]
    }

    return total
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
  val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
  val ret = Solution().trap(arr)
  println("$ret == 6")
}