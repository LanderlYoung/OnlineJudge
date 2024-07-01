package problem2092

import equalsWithoutOrder
import shouldBeEqualToWithoutOrder
import timedTest
import java.io.File

// Time Complexity O(N^N * len(meetings))
// TLE...
class Solution {
  fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
    val people = IntArray(n)
    val met = Array<MutableSet<Int>>(n) { hashSetOf() }

    var dfsCount = 0

    fun dfs(who: Int, time: Int) {
      // <<Bottle Neck>>
      // this condition makes it need to update time every iteration
      if (people[who] == time) return
      dfsCount++
      people[who] = time
      for (m in met[who]) {
        dfs(m, time)
      }
    }

    // sort meetings by time
    meetings.sortBy { it[2] }
    var index = 0
    while (index < meetings.size) {
      // collect all met people at the same time
      do {
        val m = meetings[index]
        met[m[0]].add(m[1])
        met[m[1]].add(m[0])
        index++
      } while (index < meetings.size && meetings[index - 1][2] == meetings[index][2])

      // dfs in each time-span
      val now = meetings[index - 1][2]
      dfs(0, now)
      dfs(firstPerson, now)
      met.forEachIndexed { who, list ->
        if (list.isNotEmpty()) {
          if (people[who] != 0) dfs(who, now)
        }
      }
      people.forEachIndexed { who, time -> if (time != 0) dfs(who, now) }

      // for debug only
      // println("$index -> $dfsCount, ${dfsCount / n}")

      met.forEach { it.clear() }
    }

    val result = mutableListOf<Int>()
    people.forEachIndexed { who, time ->
      if (time != 0) result += who
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
    if (result.isNotEmpty()) {
      expected shouldBeEqualToWithoutOrder expected
    }
  }

  test(50000,
    File("LeetCode/2092. Find All People With Secret/test-case-37.txt").readText()
      .trim('[').trim(']').split("],[")
      .map { it.split(',').map { it.toInt() }.toIntArray() }
      .toTypedArray<IntArray>(),
    1,
    listOf())

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


}