package problem547_2

import shouldBeEqualTo

class Solution {
  private lateinit var disjointSet: IntArray
  fun findCircleNum(isConnected: Array<IntArray>): Int {
    disjointSet = IntArray(isConnected.size) { it }

    for (i in 0..<isConnected.size) {
      for (j in 0..<i) {
        if (isConnected[i][j] == 1) {
          union(j, i)
        }
      }
    }

    var count = 0
    disjointSet.forEachIndexed { index, i -> if (i == index) count++ }
    return count
  }

  private fun union(a: Int, b: Int) {
    // make rootOf(a) point to b
    // so setOf(a) becomes sub set of b
    disjointSet[find(a)] = find(b)

    // can't do: disjointSet[a] = find(b)
    // may only change subset of a, not the whole set contains a
  }

  private fun find(a: Int): Int {
    if (disjointSet[a] != a) {
      // not root, find and flatten
      disjointSet[a] = find(disjointSet[a])
    }
    return disjointSet[a]
  }
}

fun main() {
  fun test(
    isConnected: Array<IntArray>,
    expected: Int,
  ) {
    val result = Solution().findCircleNum(isConnected)
    println("-> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(
    arrayOf(
      intArrayOf(1, 1, 0),
      intArrayOf(1, 1, 0),
      intArrayOf(0, 0, 1)
    ),
    2
  )

  test(
    arrayOf(
      intArrayOf(1, 0, 0),
      intArrayOf(0, 1, 0),
      intArrayOf(0, 0, 1),
    ),
    3
  )
}