package problem1462

import matrix2d
import shouldBeEqualTo

class Solution {
  companion object {
    const val STATE_NOT_VISITED: Byte = 0
    const val STATE_VISITING: Byte = 1
    const val STATE_VISITED: Byte = 2
  }

  /**
   * take care of the constraints
   * 2 <= numCourses <= 100
   * 1 <= queries.length <= 104
   *
   * there are lots of queries, we should pre calculate all queries first.
   */
  fun checkIfPrerequisite(numCourses: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): List<Boolean> {
    val graph = Array(numCourses) { ArrayList<Int>() }
    prerequisites.forEach { (a, b) ->
      graph[a].add(b) // a -> b
    }

    // f(i, j) -> i is prerequisite of j
    val isPrerequisite = Array(numCourses) { BooleanArray(numCourses) }
    val stack = ArrayDeque<Int>(numCourses)
    val state = ByteArray(numCourses)
    fun dfs(n: Int) {
      when (state[n]) {
        STATE_NOT_VISITED -> {
          // all element in stack is prerequisite of node n
          stack.forEach {
            isPrerequisite[it][n] = true
          }

          state[n] = STATE_VISITING
          stack.addLast(n)
          for (next in graph[n]) {
            dfs(next)
          }
          state[n] = STATE_VISITED
          stack.removeLast()
        }

        STATE_VISITED -> {
          // for every node-x, if n is prerequisite of node-x
          // all element in stack is also prerequisite of node-x
          for (x in 0..<numCourses) {
            if (x == n || isPrerequisite[n][x]) {
              stack.forEach { isPrerequisite[it][x] = true }
            }
          }
        }
      }
    }

    for (i in 0..<numCourses) {
      dfs(i)
    }

    return queries.map { (u, v) -> isPrerequisite[u][v] }
  }
}

fun main() {
  fun test(
    numCourses: Int, prerequisites: /*Array<IntArray>*/ String, queries: /*Array<IntArray>*/ String,
    expected: List<Boolean>,
  ) {
    val result = Solution().checkIfPrerequisite(numCourses, matrix2d(prerequisites), matrix2d(queries))
    println("$numCourses -> $result == $expected -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(numCourses = 2, prerequisites = "[[1,0]]", queries = "[[0,1],[1,0]]", listOf(false, true))
  test(numCourses = 2, prerequisites = "[]", queries = "[[1,0],[0,1]]", listOf(false, false))
}