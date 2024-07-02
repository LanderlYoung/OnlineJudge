package problem743

import matrix2d
import shouldBeEqualTo

/**
 * dijkstra's algorithm
 * https://www.freecodecamp.org/chinese/news/dijkstras-shortest-path-algorithm-visual-introduction/
 */
class Solution {
  fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
    // distance from k to i+1 node
    val distances = IntArray(n) { Int.MAX_VALUE }
    val visited = BooleanArray(n) // [i] means node[i+1] is visited

    distances[k - 1] = 0
    visited[k - 1] = true

    do {
      var hasNewAdjacentNode = false
      for (edge in times) {
        val nodeA = edge[0] - 1
        val nodeB = edge[1] - 1
        // this is a directed graph
        if (visited[nodeA] && !visited[nodeB]) { // b is the adjacent node of a
          // update the min distance to b,
          //  == min(a -> b, the original one)
          distances[nodeB] =
            minOf(distances[nodeB], distances[nodeA] + edge[2])
          hasNewAdjacentNode = true
        }
      }
      if (!hasNewAdjacentNode) {
        break
      }

      // after updated, choose the nearest one
      var node = -1
      var shortest = Int.MAX_VALUE
      for (i in distances.indices) {
        if (!visited[i] && distances[i] < shortest) {
          node = i
          shortest = distances[i]
        }
      }
      visited[node] = true
    } while (true)

    return distances.max().takeIf { it != Int.MAX_VALUE } ?: -1
  }
}

fun main() {
  fun test(
    times: Array<IntArray>, n: Int, k: Int,
    expected: Int,
  ) {
    val result = Solution().networkDelayTime(times, n, k)
    println("$ -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(matrix2d("[[2,1,1],[2,3,1],[3,4,1]]"), 4, 2, 2)
  test(matrix2d("[[1,2,1]]"), 2, 1, 1)
  test(matrix2d("[[1,2,1]]"), 2, 2, -1)
}