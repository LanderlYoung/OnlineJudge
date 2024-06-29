package problem547

import shouldBeEqualTo

class Solution {
  fun findCircleNum(isConnected: Array<IntArray>): Int {
    val markedCity = BooleanArray(isConnected.size)

    fun dfs(city: Int): Int {
      if (markedCity[city]) {
        // already marked
        return 0
      }

      // mare city
      markedCity[city] = true
      // mark all connected cities
      for (n in 0..<markedCity.size) {
        if (isConnected[city][n] == 1) {
          dfs(n)
        }
      }
      return 1
    }

    var provinces = 0
    for (city in 0..<markedCity.size) {
      provinces += dfs(city)
    }

    return provinces
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
      intArrayOf(0, 1, 1)
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