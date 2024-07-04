package problem2192

import matrix2d
import shouldBeEqualTo

class Solution {
  private lateinit var reverseEdge: Array<MutableList<Int>>
  fun getAncestors(n: Int, edges: Array<IntArray>): List<List<Int>> {
    reverseEdge = Array(n) { mutableListOf() }
    edges.forEach { (from, to) ->
      reverseEdge[to].add(from)
    }

    return (0..<n).map { dfsReachable(it) }
  }

  private fun dfsReachable(start: Int): List<Int> {
    val ancestors = BooleanArray(reverseEdge.size)

    // dfs from end-point in reverse order of direction
    fun dfs(node: Int) {
      if (ancestors[node]) return
      ancestors[node] = true

      for (next in reverseEdge[node]) {
        dfs(next)
      }
    }
    dfs(start)

    val result = mutableListOf<Int>()
    ancestors.forEachIndexed { index, b -> if (index!= start && b) result.add(index) }
    return result
  }
}

fun main() {
  fun test(
    n: Int, edges: String,
    expected: String,
  ) {
    val result = Solution().getAncestors(n, matrix2d(edges))
    val exp = matrix2d(expected).map { it.toList() }
    println("$n -> $result == $expected -> ${result == exp}")
    result shouldBeEqualTo exp
  }

  test(8,
    "[[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]",
    "[[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]"
  )

  test(5,
    "[[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]",
    "[[],[0],[0,1],[0,1,2],[0,1,2,3]]")

}