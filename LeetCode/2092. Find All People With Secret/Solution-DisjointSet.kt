package problem2092_3

import equalsWithoutOrder
import shouldBeEqualToWithoutOrder
import timedTest
import java.io.File

// using disjoint union
// NOTE: without rank, it throws StackOverflowError
class Solution {

  fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
    val people = IntArray(n) { it }
    val rank = IntArray(n)

    fun find(who: Int): Int {
      if (people[who] != who) {
        people[who] = find(people[who])
      }
      return people[who]
    }

    fun union(a: Int, b: Int) {
      val rootA = find(a)
      val rootB = find(b)

      if (rootA == rootB) return

      if (rank[rootA] < rank[rootB]) {
        people[rootA] = rootB
      } else if (rank[rootA] > rank[rootB]) {
        people[rootB] = rootA
      } else {
        people[rootA] = rootB
        rank[rootB]++
      }
    }

    fun isolate(a: Int) {
      people[a] = a
      rank[a] = 0
    }

    union(0, firstPerson)

    meetings.sortBy { it[2] } // sort meetings by time
    var index = 0
    while (index < meetings.size) {
      // collect all met people at the same time
      val start = index
      do {
        val m = meetings[index]
        union(m[0], m[1])

        index++
      } while (index < meetings.size && meetings[index - 1][2] == meetings[index][2])

      // dis-union those who don't know
      val secretHolder = find(0)
      for (i in start..<index) {
        val m = meetings[i]
        // if x don't know, y also don't know
        if (find(m[0]) != secretHolder) {
          isolate(m[0])
          isolate(m[1])
        }
      }
    }

    val result = mutableListOf<Int>()
    val secretHolder = find(0)
    for (i in people.indices) {
      if (find(i) == secretHolder) {
        result += i
      }
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