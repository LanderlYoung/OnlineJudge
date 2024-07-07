package problem210

import matrix2d
import shouldBeEqualTo
import java.util.ArrayList

/**
 * refers to [problem207_3.Solution]
 */
class Solution {

  // like GC algorithm, do triple color mark, last order iteration of a map
  companion object {
    const val STATE_NOT_VISITED: Byte = 0
    const val STATE_VISITING: Byte = 1
    const val STATE_VISITED: Byte = 2
  }

  fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val graph = Array(numCourses) { ArrayList<Int>() }
    prerequisites.forEach { (a, b) ->
      graph[b].add(a) // b -> a
    }


    val stack = ArrayList<Int>()

    val state = ByteArray(numCourses)
    fun dfs(n: Int) {
      when (state[n]) {
        STATE_NOT_VISITED -> {
          state[n] = STATE_VISITING

          for (next in graph[n]) {
            dfs(next)
          }

          state[n] = STATE_VISITED

          stack.add(n)
        }

        STATE_VISITING -> {
          // n is visiting, we met n again, there must be a circle
          throw RuntimeException("Circle Detected!")
        }

        STATE_VISITED -> {
          // n is already visited, means all outgoing node of n is already visited, we just ignore the node
        }
      }
    }

    try {
      for (i in 0..<numCourses) {
        dfs(i)
      }
    } catch (e: RuntimeException) {
      return intArrayOf()
    }

    // stack.reversed() is a valid topological order

    return stack.reversed().toIntArray()
  }
}

fun main() {

}