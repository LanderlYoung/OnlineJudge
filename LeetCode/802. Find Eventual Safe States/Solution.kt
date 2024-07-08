package problem802

import matrix1d
import matrix2d
import shouldBeEqualTo

class Solution {
  companion object {
    private const val STATE_IDLE: Byte = 0
    private const val STATE_VISITING: Byte = 1
    private const val STATE_VISITED: Byte = 2
    private const val STATE_TO_DELETE: Byte = 4
  }

  fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
    val state = ByteArray(graph.size)

    fun dfs(node: Int): Boolean { // returns found circle
      when (state[node]) {
        STATE_IDLE -> {
          state[node] = STATE_VISITING
          var foundCircle = false
          for (next in graph[node]) {
            foundCircle = dfs(next) || foundCircle
          }
          state[node] = if (foundCircle) STATE_TO_DELETE else STATE_VISITED
          return foundCircle
        }

        STATE_VISITING -> {
          // found circle
          state[node] = STATE_TO_DELETE
          return true
        }

        STATE_TO_DELETE -> {
          // found old circle
          return true
        }

        // visited
        else -> return false
      }
    }

    return graph.indices.filter { !dfs(it) }
  }
}

fun main() {
  fun test(
    graph: String,
    expected: String,
  ) {
    val exp = matrix1d(expected).toList()
    val result = Solution().eventualSafeNodes(matrix2d(graph))
    println("$ -> $result == $expected -> ${result == exp}")
    result shouldBeEqualTo exp
  }

  test("[[0],[2,3,4],[3,4],[0,4],[]]", "[4]")
  test("[[1,2],[2,3],[5],[0],[5],[],[]]", "[2,4,5,6]")
  test("[[1,2,3,4],[1,2],[3,4],[0,4],[]]", "[4]")
}