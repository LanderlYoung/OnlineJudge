package problem743_3

import matrix2d
import shouldBeEqualTo
import java.util.PriorityQueue

/**
 * dijkstra's algorithm
 * https://www.freecodecamp.org/chinese/news/dijkstras-shortest-path-algorithm-visual-introduction/
 */
class Solution {
  fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
    val graph = Array(n) { IntArray(n) { Int.MAX_VALUE } }
    for (edge in times) {
      graph[edge[0] - 1][edge[1] - 1] = edge[2]
      // this is a directional graph
      // graph[edge[1] - 1][edge[0] - 1] = edge[2]
    }

    //// <<< Dijkstra's shortest path algorithm here >>>

    // distance from k to i+1 node
    // positive means calculated, but yet to determine
    val distances = IntArray(n) { Int.MAX_VALUE }
    val visited = BooleanArray(n) // [i] means node[i+1] is visited

    // distance <index, distance>
    val nearestDistance = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
    // problem: A node's distance maybe update to a smaller one, but heap can not update value.
    // solution: put a new value to "override" previous one -- smaller value be at the front of a queue
    // Q: how to ignore duplicated value(s)?
    // A: when a "valid" value is consumed, the node is visited, thus distance[node] < overridden one
    // Also, if we still have the visited array, check if node is already visited is also viable.

    // initial: update the distance of initial node
    // distances[k - 1] = 0
    distances[k - 1] = 0
    nearestDistance.offer(k - 1 to 0)

    while (nearestDistance.isNotEmpty()) {
      // find nearest node -- using Heap
      val (node, nodeDistance) = nearestDistance.poll()
      if (visited[node]) {
        continue
      }

      visited[node] = true

      // update adjacent node distance
      for (j in 0..<n) {
        if (!visited[j] && graph[node][j] < Int.MAX_VALUE) {
          val newDistance = nodeDistance + graph[node][j]
          if (newDistance < distances[j]) {
            distances[j] = newDistance
            nearestDistance.offer(j to newDistance)
          }
        }
      }
    }

    val max = distances.max()
    if (max == Int.MAX_VALUE) {
      return -1
    }
    return max
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