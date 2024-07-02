package problem2101_2

import matrix2d
import shouldBeEqualTo


class Solution {
  fun maximumDetonation(bombs: Array<IntArray>): Int {
    val parent = IntArray(bombs.size) { it }
    val rank = IntArray(bombs.size)

    fun find(a: Int): Int {
      if (parent[a] != a) {
        parent[a] = find(parent[a])
      }
      return parent[a]
    }

    fun union(a: Int, b: Int) {
      if (a == b) return
      val rootA = find(a)
      val rootB = find(b)
      when {
        rank[rootA] > rank[rootB] ->
          parent[b] = rootA

        rank[rootA] < rank[rootB] ->
          parent[a] = rootB

        else -> {
          parent[b] = rootA
          rank[a]++
        }
      }
    }

    bombs.sortBy { -it[2] }
    for (i in bombs.indices) {
      for (j in i + 1..<bombs.size) {
        if (canDetonate(bombs[i], bombs[j])) {
          union(i, j)
        }
      }
    }

    val count = IntArray(bombs.size)
    for (i in bombs.indices) {
      count[find(i)]++
    }

    return count.max()
  }

  // a -> b
  private fun canDetonate(a: IntArray, b: IntArray): Boolean {
    val distanceSquare = (a[0] - b[0]).toLong() * (a[0] - b[0]) + (a[1] - b[1]).toLong() * (a[1] - b[1])
    val radius = a[2].toLong()
    val inRange = distanceSquare <= radius * radius
    return inRange
  }
}

fun main() {
  fun test(
    bombs: Array<IntArray>,
    expected: Int,
  ) {
    val result = Solution().maximumDetonation(bombs)
    println("$ -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(matrix2d("[[634,440,278],[748,509,396],[995,881,251],[704,214,341],[832,972,238],[987,384,156],[378," +
      "988,402],[743,557,252],[814,868,196],[131,922,199],[13,398,444],[464,607,241],[426,128,81]]"), 12)

  test(matrix2d("[[855,82,158],[17,719,430],[90,756,164],[376,17,340],[691,636,152],[565,776,5],[464,154,271]," +
      "[53,361,162],[278,609,82],[202,927,219],[542,865,377],[330,402,270],[720,199,10],[986,697,443],[471,296,69]," +
      "[393,81,404],[127,405,177]]"), 9)

  test(
    matrix2d("[[54,95,4],[99,46,3],[29,21,3],[96,72,8],[49,43,3],[11,20,3],[2,57,1],[69,51,7],[97,1,10],[85," +
        "45,2],[38,47,1],[83,75,3],[65,59,3],[33,4,1],[32,10,2],[20,97,8],[35,37,3]]"),
    1
  )

  test(
    arrayOf(
      intArrayOf(1, 1, 100000),
      intArrayOf(100000, 100000, 1),
    ),
    1
  )

  test(
    arrayOf(
      intArrayOf(2, 1, 3),
      intArrayOf(6, 1, 4)
    ),
    2
  )

  test(
    arrayOf(
      intArrayOf(1, 1, 5),
      intArrayOf(10, 10, 5)
    ),
    1
  )

  test(
    arrayOf(
      intArrayOf(1, 2, 3),
      intArrayOf(2, 3, 1),
      intArrayOf(3, 4, 2),
      intArrayOf(4, 5, 3),
      intArrayOf(5, 6, 4)
    ),
    5
  )


}