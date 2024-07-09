package problem1203

import matrix1d
import matrix2d
import timedTest

class Solution {
  fun sortItems(n: Int, m: Int, group: IntArray, beforeItems: List<List<Int>>): IntArray {
    // all non grouped numbers to assign a new group
    // so we only deal with groups
    var groupNum = m
    for (i in group.indices) {
      if (group[i] == -1) {
        group[i] = groupNum++
      }
    }

    val itemsInDegree = IntArray(n)
    val groupInDegree = IntArray(groupNum)

    val itemsGraph = MutableList(n) { mutableListOf<Int>() }
    val groupGraph = MutableList(groupNum) { mutableSetOf<Int>() }
    for (i in beforeItems.indices) {
      for (b in beforeItems[i]) {
        // b -> i
        itemsGraph[b].add(i)
        itemsInDegree[i]++

        if (group[b] != group[i]) {
          groupGraph[group[b]].add(group[i])
        }
      }
    }
    for (i in groupGraph.indices) {
      for (g in groupGraph[i]) {
        groupInDegree[g]++
      }
    }

    // step 1. item DAG
    val groups = Array(groupNum) { mutableListOf<Int>() }
    val queue = ArrayDeque<Int>()
    for (i in itemsInDegree.indices) {
      if (itemsInDegree[i] == 0) {
        queue.add(i)
      }
    }
    var count = 0
    while (queue.isNotEmpty()) {
      val num = queue.removeFirst()
      groups[group[num]].add(num)
      count++

      for (next in itemsGraph[num]) {
        if (--itemsInDegree[next] == 0) {
          queue.add(next)
        }
      }
    }
    // item dag failed
    if (count != n) {
      return intArrayOf()
    }

    // step 2. group DAG
    val orderedGroup = ArrayList<List<Int>>(groupNum)
    for (i in groupInDegree.indices) {
      if (groupInDegree[i] == 0) {
        queue.add(i)
      }
    }
    while (queue.isNotEmpty()) {
      val g = queue.removeFirst()
      orderedGroup.add(groups[g])

      for (nextG in groupGraph[g]) {
        if (--groupInDegree[nextG] == 0) {
          queue.add(nextG)
        }
      }
    }
    if (orderedGroup.size != groupNum) {
      return intArrayOf()
    }

    return orderedGroup.flatten().toIntArray()
  }
}

fun main() = timedTest(repeat = 10) {
  fun test(
    n: Int, m: Int, group: String, beforeItems: String,
    expected: String,
  ) {
    val exp = matrix1d(expected)
    val result = Solution().sortItems(n, m, matrix1d(group), matrix2d(beforeItems).map { it.toList() })
    println("$ -> ${result.contentToString()} == ${exp.contentToString()} -> ${result.contentEquals(exp)}")
  }

  test(5, 3, "[0,0,2,1,0]", "[[3],[],[],[],[1,3,2]]", "[3,2,0,1,4]")
  test(n = 8,
    m = 2,
    group = "[-1,-1,1,0,0,1,0,-1]",
    beforeItems = "[[],[6],[5],[6],[3,6],[],[],[]]",
    expected = "[6,3,4,1,5,2,0,7]")
  test(n = 8, m = 2, group = "[-1,-1,1,0,0,1,0,-1]", beforeItems = "[[],[6],[5],[6],[3],[],[4],[]]", expected = "[]")
}