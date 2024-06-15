package problem407

import kotlin.math.max
import kotlin.math.min

// failed...
class Solution {
  fun trapRainWater(heightMap: Array<IntArray>): Int {
    if (heightMap.isEmpty()) return 0

    var total = 0

    val rows = heightMap.size
    val columns = heightMap[0].size

    val cellTrappedWaterHeight = Array(rows) { IntArray(columns) }
    val cellTrappedWaterHeightModified = Array(rows) { IntArray(columns) }

    // rows
    for (row in 0..<rows) {
      val trapped = cellTrappedWaterHeight[row]
      val heightArray = heightMap[row]

      var left = 0
      var right = columns - 1

      var leftMax = 0
      var rightMax = 0

      while (left <= right) {
        leftMax = max(leftMax, heightArray[left])
        rightMax = max(rightMax, heightArray[right])

        if (leftMax < rightMax) {
          trapped[left] = leftMax
          left++
        } else {
          trapped[right] = rightMax
          right--
        }
      }
    }

    // columns
    for (column in 0..<columns) {
      var left = 0
      var right = rows - 1

      var leftMax = 0
      var rightMax = 0

      while (left <= right) {
        leftMax = max(leftMax, heightMap[left][column])
        rightMax = max(rightMax, heightMap[right][column])

        val columnTrappedWatterHeight: Int
        val index: Int
        if (leftMax < rightMax) {
          columnTrappedWatterHeight = leftMax
          index = left
          left++
        } else {
          columnTrappedWatterHeight = rightMax
          index = right
          right--
        }

        val t = min(columnTrappedWatterHeight, cellTrappedWaterHeight[index][column])
        cellTrappedWaterHeight[index][column] = t
      }
    }


    return total
  }
}

fun main(args: Array<String>) {
  run {
    // [[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]
    println("14 = " + Solution().trapRainWater(arrayOf(
      intArrayOf(12, 13, 1, 12),
      intArrayOf(13, 4, 13, 12),
      intArrayOf(13, 8, 10, 12),
      intArrayOf(12, 13, 12, 12),
      intArrayOf(13, 13, 13, 13),
    )))

    // Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
    // Output: 10

    println("10 = " + Solution().trapRainWater(arrayOf(
      intArrayOf(3, 3, 3, 3, 3),
      intArrayOf(3, 2, 2, 2, 3),
      intArrayOf(3, 2, 1, 2, 3),
      intArrayOf(3, 2, 2, 2, 3),
      intArrayOf(3, 3, 3, 3, 3),
    )))


    // [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
    println(" = " + Solution().trapRainWater(arrayOf(
      intArrayOf(1, 4, 3, 1, 3, 2),
      intArrayOf(3, 2, 1, 3, 2, 4),
      intArrayOf(2, 3, 3, 2, 3, 1),
    )))


  }
}