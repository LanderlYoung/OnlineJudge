package problem407_2

import java.util.*
import kotlin.math.max

class Solution {
  data class Point(
    val m: Int,
    val n: Int,
    val h: Int,
  )

  fun trapRainWater(heightMap: Array<IntArray>): Int {
    if (heightMap.size <= 2 || heightMap[0].size <= 2) return 0

    val m = heightMap.size
    val n = heightMap[0].size

    val waterHeight = Array(m) { IntArray(n) }
    val visited = Array(m) { BooleanArray(n) }

    val priorityQueue = PriorityQueue<Point> { lhs, rhs -> lhs.h - rhs.h }
    var total = 0

    // out-most
    for (i in 0..<m) {
      var ni = 0
      waterHeight[i][ni] = heightMap[i][ni]
      visited[i][ni] = true
      priorityQueue.offer(Point(i, ni, heightMap[i][ni]))

      ni = n - 1
      waterHeight[i][ni] = heightMap[i][ni]
      visited[i][ni] = true
      priorityQueue.offer(Point(i, ni, heightMap[i][ni]))
    }

    for (i in 1..<(n - 1)) {
      var mi = 0
      waterHeight[mi][i] = heightMap[mi][i]
      visited[mi][i] = true
      priorityQueue.offer(Point(mi, i, heightMap[mi][i]))

      mi = m - 1
      waterHeight[mi][i] = heightMap[mi][i]
      visited[mi][i] = true
      priorityQueue.offer(Point(mi, i, heightMap[mi][i]))
    }

    while (priorityQueue.isNotEmpty()) {
      // visit from the lowest point
      // this is important!
      val p = priorityQueue.poll()

      fun visit(dm: Int, dn: Int)  {
        val newM = p.m + dm
        val newN = p.n + dn
        if (
        // valid point
          newM in 0..<m && newN in 0..<n
          // not visited
          && visited[newM][newN]
        ) {
          val newH = heightMap[newM][newN]
          if (p.h > newH) {
            // outer is higher, water kept in here
            val kept = p.h - newH
            total += kept
          }
          waterHeight[newM][newN] = max(p.h, newH)
          visited[newM][newN] = true

          priorityQueue.offer(Point(newM, newN, max(p.h, newH)))
        }
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

    // println("10 = " + Solution().trapRainWater(arrayOf(
    //   intArrayOf(3, 3, 3, 3, 3),
    //   intArrayOf(3, 2, 2, 2, 3),
    //   intArrayOf(3, 2, 1, 2, 3),
    //   intArrayOf(3, 2, 2, 2, 3),
    //   intArrayOf(3, 3, 3, 3, 3),
    // )))
    //
    //
    // // [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
    // println(" = " + Solution().trapRainWater(arrayOf(
    //   intArrayOf(1, 4, 3, 1, 3, 2),
    //   intArrayOf(3, 2, 1, 3, 2, 4),
    //   intArrayOf(2, 3, 3, 2, 3, 1),
    // )))


  }
}