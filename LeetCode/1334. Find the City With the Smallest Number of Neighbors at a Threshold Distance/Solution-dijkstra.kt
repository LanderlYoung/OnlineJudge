package problem1334

import matrix2d
import shouldBeEqualTo
import timedTest

class Solution {
  fun findTheCity(n: Int, edges: Array<IntArray>, distanceThreshold: Int): Int {
    val graph = Array(n) { IntArray(n) { Int.MAX_VALUE } }
    for (e in edges) {
      // bidirectional edge
      graph[e[0]][e[1]] = e[2]
      graph[e[1]][e[0]] = e[2]
    }

    val distance = IntArray(n) { Int.MAX_VALUE }
    val visited = BooleanArray(n)

    fun dijkstra(from: Int): Int {
      var reached = 0

      // initial node
      distance[from] = 0
      while (true) {
        var node = -1
        for (i in 0..<n) {
          if (!visited[i] && (node == -1 || distance[i] < distance[node])) {
            node = i
          }
        }

        if (node == -1) return reached // all visited
        if (distance[node] == Int.MAX_VALUE) return reached // unreachable node
        if (distance[node] > distanceThreshold) return reached // reached max distance, no need to search further

        reached++
        visited[node] = true

        // update adjacent
        for (j in 0..<n) {
          if (!visited[j] && graph[node][j] != Int.MAX_VALUE) {
            distance[j] = minOf(distance[j],
              distance[node] + graph[node][j]
            )
          }
        }
      }
    }

    var city = 0
    var count = Int.MAX_VALUE
    for (i in 0..<n) {
      val c = dijkstra(i)
      if (c <= count) {
        count = c
        city = i
      }

      distance.fill(Int.MAX_VALUE)
      visited.fill(false)
    }

    return city
  }
}

fun main() = timedTest {
  fun test(
    n: Int, edges: Array<IntArray>, distanceThreshold: Int,
    expected: Int,
  ) {
    val result = Solution().findTheCity(n, edges, distanceThreshold)
    println("-> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(n = 4, edges = matrix2d("[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]"), distanceThreshold = 4, expected = 3)
  test(n = 5,
    edges = matrix2d("[[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]]"),
    distanceThreshold = 2,
    expected = 0)

}