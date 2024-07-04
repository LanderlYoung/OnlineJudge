package problem127

import shouldBeEqualTo

class Solution {
  private lateinit var graph: Array<HashSet<Int>>
  private var end: Int = 0
  fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    buildGraph(wordList, beginWord, endWord)
    if (end == -1) return 0

    val min = bfs(0)
    return min.takeIf { it != Int.MAX_VALUE } ?: 0
  }

  private fun bfs(start: Int): Int {
    val visited = BooleanArray(graph.size)
    val queue = ArrayDeque<Int>(0)
    queue.addLast(start)
    visited[start] = true

    var depth = 1
    while (queue.isNotEmpty()) {
      repeat(queue.size) {
        val n = queue.removeFirst()
        if (n == end) {
          return depth
        }

        for (next in graph[n]) {
          if (!visited[next]) {
            visited[next] = true
            queue.addLast(next)
          }
        }
      }

      depth++
    }

    return Int.MAX_VALUE
  }

  private fun buildGraph(wordList: List<String>, beginWord: String, endWord: String) {
    val list = if (!wordList.contains(beginWord)) {
      listOf(beginWord) + wordList
    } else {
      val v = wordList.toMutableList()
      v.remove(beginWord)
      v.add(0, beginWord)
      v
    }

    end = list.indexOf(endWord)
    graph = Array(list.size) { HashSet<Int>() }

    // build graph
    repeat(list.size) { i ->
      val w = list[i]
      repeat(list.size) { j ->
        if (isAdjacent(w, list[j])) {
          graph[i].add(j)
          graph[j].add(i)
        }
      }
    }

  }

  private fun isAdjacent(word1: String, word2: String): Boolean {
    var diff = 0
    for (i in word1.indices) {
      if (word1[i] != word2[i]) {
        diff++
      }
      if (diff > 1) {
        return false
      }
    }

    return diff == 1
  }
}

fun main() {
  fun test(
    beginWord: String, endWord: String, wordList: List<String>,
    expected: Int,
  ) {
    val result = Solution().ladderLength(beginWord, endWord, wordList)
    println("$ -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test("hit", "cog", listOf("hot", "dot", "dog", "lot", "log", "cog"), 5)
  test("hit", "cog", listOf("hot", "dot", "dog", "lot", "log"), 0)

}