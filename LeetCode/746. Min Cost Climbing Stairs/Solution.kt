package problem746

import kotlin.math.min

class Solution {
  val minCost = IntArray(1001) { -1 }.also {
    it[0] = 0
    it[1] = 0
  }

  fun minCostClimbingStairs(cost: IntArray): Int {
    return minCost(cost.size, cost)
  }

  private fun minCost(i: Int, cost: IntArray): Int {
    if (minCost[i] != -1) {
      return minCost[i]
    }

    val m_1 = minCost(i - 1, cost) + cost[i - 1]
    val m_2 = minCost(i - 2, cost) + cost[i - 2]

    val ret = min(m_1, m_2)
    minCost[i] = ret

    return ret
  }
}


// test
fun test(
  // input
  input: IntArray,

  // expected
  expected: Int,
) {

  val result = Solution().minCostClimbingStairs(input)
  println("$input -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  test(intArrayOf(10, 15, 20), 15)
  test(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1), 6)

}