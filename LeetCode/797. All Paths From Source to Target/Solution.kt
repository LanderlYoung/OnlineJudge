package problem797

import equalsWithoutOrder
import shouldBeEqualToWithoutOrder

class Solution {
  fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
    val paths = ArrayList<List<Int>>()
    val path = ArrayList<Int>()

    fun dfs(node: Int) {
      path.add(node)

      if (node == graph.size - 1) { // reaches end
        paths.add(ArrayList(path)) // remember to make a copy
      } else {
        val next = graph[node]
        for (n in next) {
          dfs(n)
        }
      }

      path.removeLast() // remove node
    }

    dfs(0)

    return paths
  }
}

fun main() {
  fun test(
    graph: Array<IntArray>,
    expected: List<List<Int>>,
  ) {
    val result = Solution().allPathsSourceTarget(graph)
    println("-> ${result} == ${expected} -> ${result equalsWithoutOrder expected}")
    result shouldBeEqualToWithoutOrder expected
  }

  test(
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(3),
      intArrayOf(3),
      intArrayOf()
    ),
    listOf(
      listOf(0, 1, 3),
      listOf(0, 2, 3)
    )
  )

  test(
    arrayOf(
      intArrayOf(4, 3, 1),
      intArrayOf(3, 2, 4),
      intArrayOf(3),
      intArrayOf(4),
      intArrayOf()
    ),
    listOf(
      listOf(0, 4),
      listOf(0, 3, 4),
      listOf(0, 1, 3, 4),
      listOf(0, 1, 2, 3, 4),
      listOf(0, 1, 4),
    )
  )


}