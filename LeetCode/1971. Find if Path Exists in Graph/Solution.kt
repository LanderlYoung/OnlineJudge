package problem1971

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int
import shouldBeEqualTo

class Solution {
  fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val marked = BooleanArray(n)

    val edgesMap = HashMap<Int, MutableList<IntArray>>()
    for (e in edges) {
      edgesMap.getOrPut(e[0]) { mutableListOf() }.add(e)
      edgesMap.getOrPut(e[1]) { mutableListOf() }.add(e)
    }

    fun dfs(n: Int) {
      if (marked[n]) return
      marked[n] = true
      for (edge in edgesMap[n] ?: emptyList()) {
        if (edge[0] == n) {
          dfs(edge[1])
        } else if (edge[1] == n) {
          dfs(edge[0])
        }
      }
    }

    dfs(source)

    return marked[destination]
  }
}

fun main() {
  fun test(
    n: Int, edges: Array<IntArray>, source: Int, destination: Int,
    expected: Boolean,
  ) {
    val result = Solution().validPath(n, edges, source, destination)
    println("-> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(
    3,
    arrayOf(
      intArrayOf(0, 1),
      intArrayOf(1, 2),
      intArrayOf(2, 0)
    ),
    0,
    2,
    true
  )

  test(
    6,
    arrayOf(
      intArrayOf(0, 1),
      intArrayOf(0, 2),
      intArrayOf(3, 5),
      intArrayOf(5, 4),
      intArrayOf(4, 3),
    ),
    0,
    5,
    false
  )
}