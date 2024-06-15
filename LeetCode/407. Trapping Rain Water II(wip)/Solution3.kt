package problem407_3

import java.util.*

// from https://leetcode.cn/problems/trapping-rain-water-ii/solutions/162877/you-xian-dui-lie-de-si-lu-jie-jue-jie-yu-shui-ii-b
// for debug
class Solution {
  fun trapRainWater(heights: Array<IntArray>): Int {
    if (heights.isEmpty()) return 0
    val n = heights.size
    val m = heights[0].size
    // 用一个vis数组来标记这个位置有没有被访问过
    val vis = Array(n) { BooleanArray(m) }
    // 优先队列中存放三元组 [x,y,h] 坐标和高度
    val pq = PriorityQueue<IntArray> { o1, o2 -> o1[2] - o2[2] }

    // 先把最外一圈放进去
    for (i in 0 until n) {
      for (j in 0 until m) {
        if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
          pq.offer(intArrayOf(i, j, heights[i][j]))
          vis[i][j] = true
        }
      }
    }
    var res = 0
    // 方向数组，把dx和dy压缩成一维来做
    val dirs = intArrayOf(-1, 0, 1, 0, -1)
    while (pq.isNotEmpty()) {
      val poll = pq.poll()
      // 看一下周围四个方向，没访问过的话能不能往里灌水
      for (k in 0..3) {
        val nx = poll[0] + dirs[k]
        val ny = poll[1] + dirs[k + 1]
        // 如果位置合法且没访问过
        if (nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny]) {
          // 如果外围这一圈中最小的比当前这个还高，那就说明能往里面灌水啊
          if (poll[2] > heights[nx][ny]) {
            res += poll[2] - heights[nx][ny]
          }
          // 如果灌水高度得是你灌水后的高度了，如果没灌水也要取高的
          pq.offer(intArrayOf(nx, ny, maxOf(heights[nx][ny], poll[2])))
          vis[nx][ny] = true
        }
      }
    }
    return res
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