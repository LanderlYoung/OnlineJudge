package problem2092_2

import equalsWithoutOrder
import shouldBeEqualToWithoutOrder
import timedTest
import java.io.File

class Solution {
  fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
    val people = BooleanArray(n)
    val met = HashMap<Int, MutableSet<Int>>()

    fun dfs(who: Int) {
      if (people[who]) return
      people[who] = true
      for (m in met[who] ?: emptySet()) {
        dfs(m)
      }
    }
    dfs(0)
    dfs(firstPerson)

    meetings.sortBy { it[2] } // sort meetings by time
    var index = 0
    while (index < meetings.size) {
      // collect all met people at the same time
      do {
        val m = meetings[index]
        // not already known
        if (!(people[m[0]] && people[m[1]])) {
          met.getOrPut(m[0]) { hashSetOf() }.add(m[1])
          met.getOrPut(m[1]) { hashSetOf() }.add(m[0])
        }
        index++
      } while (index < meetings.size && meetings[index - 1][2] == meetings[index][2])

      // incremental dfs in each time-span
      met.forEach { (who, conn) ->
        conn.forEach { toWho ->
          if (people[who] && !people[toWho]) dfs(toWho)
          if (!people[who] && people[toWho]) dfs(who)
        }
      }

      met.clear()
    }

    val result = mutableListOf<Int>()
    people.forEachIndexed { who, _ ->
      if (people[who]) result += who
    }
    return result
  }
}

fun main() = timedTest {
  fun test(
    n: Int, meetings: Array<IntArray>, firstPerson: Int,
    expected: List<Int>,
  ) {
    val result = Solution().findAllPeople(n, meetings, firstPerson)
    println("$ -> ${result} == ${expected} -> ${result equalsWithoutOrder expected}")
    if (expected.isNotEmpty()) {
      result shouldBeEqualToWithoutOrder expected
    }
  }

  // [[3,4,2],[1,2,1],[2,3,1]]
  test(5,
    arrayOf(
      intArrayOf(3, 4, 2),
      intArrayOf(1, 2, 1),
      intArrayOf(2, 3, 1)
    ),
    1,
    listOf(0, 1, 2, 3, 4)
  )

  // Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1 Output: [0,1,2,3,5]
  test(6,
    arrayOf(
      intArrayOf(1, 2, 5),
      intArrayOf(2, 3, 8),
      intArrayOf(1, 5, 10),
    ),
    1,
    listOf(0, 1, 2, 3, 5)
  )

  // Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3 Output: [0,1,3]
  test(4,
    arrayOf(
      intArrayOf(3, 1, 3),
      intArrayOf(1, 2, 2),
      intArrayOf(0, 3, 3),
    ),
    3,
    listOf(0, 1, 3)
  )

  // Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1 Output: [0,1,2,3,4]
  test(5,
    arrayOf(
      intArrayOf(3, 4, 2),
      intArrayOf(1, 2, 1),
      intArrayOf(2, 3, 1)
    ), 1,
    listOf(0, 1, 2, 3, 4)
  )

  test(50000,
    File("LeetCode/2092. Find All People With Secret/test-case-37.txt").readText()
      .trim('[').trim(']').split("],[")
      .map { it.split(',').map { it.toInt() }.toIntArray() }
      .toTypedArray<IntArray>(),
    1,
    listOf())

}