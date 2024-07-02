package problem743_2

import matrix2d
import shouldBeEqualTo

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

    // the node visited latter has larger distance
    var maxDistance = 0

    //// <<< Dijkstra's shortest path algorithm here >>>
    // distance from k to i+1 node
    val distances = IntArray(n) { Int.MAX_VALUE }
    val visited = BooleanArray(n) // [i] means node[i+1] is visited

    // initial: update the distance of initial node
    distances[k - 1] = 0
    while (true) {
      // find nearest node
      var node = -1
      for (i in 0..<n) {
        if (!visited[i] &&
          // find any non visited node or nearest node
          (node == -1 || distances[i] < distances[node])
        ) {
          node = i
        }
      }

      if (node == -1) return maxDistance // all visited
      if (distances[node] == Int.MAX_VALUE) return -1 // can not reach

      // node is determined
      visited[node] = true

      maxDistance = distances[node]

      // update adjacent node distance
      for (j in 0..<n) {
        if (!visited[j] && graph[node][j] < Int.MAX_VALUE) {
          distances[j] = minOf(
            distances[j],
            distances[node] + graph[node][j]
          )
        }
      }
    }
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