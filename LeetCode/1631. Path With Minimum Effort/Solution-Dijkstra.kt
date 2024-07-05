package problem1631

import matrix2d
import shouldBeEqualTo
import java.util.PriorityQueue
import kotlin.math.absoluteValue

// 2D dijkstra
class Solution {
  fun minimumEffortPath(heights: Array<IntArray>): Int {
    val distances = Array(heights.size) { IntArray(heights[0].size) { Int.MAX_VALUE } }

    // row, col to distance
    val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
    queue.add(Triple(0, 0, 0))
    distances[0][0] = 0

    while (queue.isNotEmpty()) {
      val (r, c, distance) = queue.poll()
      if (distance > distances[r][c]) {
        // overridden data, ignore
        continue
      }

      // found adjacent nodes
      fun visit(row: Int, col: Int) {
        if (row in heights.indices && col in heights[0].indices) {
          val newDistance = maxOf(distance, (heights[row][col] - heights[r][c]).absoluteValue)
          if (newDistance < distances[row][col]) {
            distances[row][col] = newDistance
            queue.offer(Triple(row, col, newDistance))
          }
        }
      }
      visit(r - 1, c)
      visit(r + 1, c)
      visit(r, c - 1)
      visit(r, c + 1)
    }

    return distances[heights.size - 1][heights[0].size - 1]
  }
}

fun main() {
  fun test(
    heights: /*Array<IntArray>*/ String,
    expected: Int,
  ) {
    val result = Solution().minimumEffortPath(matrix2d(heights))
    println("$ -> $result == $expected -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test("[[1,2,2],[3,8,2],[5,3,5]]", 2)
  test("[[1,2,3],[3,8,4],[5,3,5]]", 1)
  test("[[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]", 0)
}