package problem1334_3

import matrix2d
import shouldBeEqualTo
import timedTest

// Floyd-Warshall algorithm -- the DP solution
class Solution {
  /**
   * ## 1. define sub problem
   * `f(k, i, j)` -> use node < k, the shortest path from i to j
   * node: edge condition
   *
   * ## 2. recursion formula
   * `f(k, i, j)` ->
   * - case A: if node(k-1) is NOT SELECTED,
   *          == `f(k - 1, i, j)`, we don't need node-k anyway
   *
   * - case B: if node-(k-1) is SELECTED, the path can be split into two parts: i->k, k->j
   *          == `f(k - 1, i, k - 1) + f(k - 1, k - 1, j)`
   *
   * `f(k, i, j)` = `min(case A, case B)`
   *            = `min(f(k - 1, i, j), f(k - 1, i, k - 1) + f(k - 1, k - 1, j))`
   *
   *  `f(0, i, j)` -> `w(i ,j)`, directly connect i -> j, if no valid path, it's +INF
   *
   * ## 3. iterate order
   * Examining the recursion formula, we noticed that k goes ascending.
   * i,j,k don't have a certain order.
   *
   * <<< Optimization in here >>>
   * ## 4. optimize space usage
   * Examining the recursion formula, we noticed only `k - 1` layer is used.
   *
   * Q: Can we reduce space from N*N*N to N*N?
   * A: Consider use two layer swap, previous layer(k-1) and current layer(k)
   *    YES, we can use to layers swapping, 2 * N * N
   *
   * Q: Can we use only one layer of N*N?
   * A: Sad thing is that i,j,k don't have a certain order.
   * So when we do the formula we depend on f(k-1, i/k-1, j/k-1)
   *
   * 1. `f(k-1, i, j)`, when we do `f(k, i, j)`, the old value is not touched/overridden.
   *
   * 2. `f(k-1, i, k-1)`/`f(k-1, k-1, j)` may be overridden to `f(k, i, k - 1)`/`f(k, k-1, j)`
   *    However `f(k, i, k - 1)`/`f(k, k-1, j)` used k-1 as end node, so it won't be the middle node, so we have the
   *    super clever Conclusion: `f(k, i, k - 1) == f(k -1, i, k - 1)`, also `f(k, k - 1, j) == f(k - 1, k - 1, j)`.
   *    As a consequence, the data overriding is not a problem!
   */
  fun findTheCity(n: Int, edges: Array<IntArray>, distanceThreshold: Int): Int {
    val graph = Array(n) { IntArray(n) { Int.MAX_VALUE / 2 } }
    for (e in edges) {
      // bidirectional edge
      graph[e[0]][e[1]] = e[2]
      graph[e[1]][e[0]] = e[2]
    }

    val f = Array(n) { IntArray(n) }

    // initial value
    for (i in 0..<n) {
      for (j in 0..<n) {
        f[i][j] = graph[i][j]
      }
    }

    // do the iteration
    for (k in 1..n) {
      for (i in 0..<n) {
        for (j in 0..<n) {
          f[i][j] = minOf(
            f[i][j],
            f[i][k - 1] + f[k - 1][j] // careful about int overflow
          )
        }
      }
    }

    var count = Int.MAX_VALUE
    var city = 0
    for (i in 0..<n) {
      f[i][i] = Int.MAX_VALUE
      val c = f[i].count { it <= distanceThreshold }
      if (c <= count) {
        count = c
        city = i
      }
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