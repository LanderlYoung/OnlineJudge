package problem1311

import matrix2d
import shouldBeEqualTo
import smatrix2d

class Solution {
  fun watchedVideosByFriends(
    watchedVideos: List<List<String>>,
    friends: Array<IntArray>,
    id: Int,
    level: Int,
  ): List<String> {
    val visited = BooleanArray(friends.size)

    var l = 0
    val queue = ArrayDeque<Int>()
    queue.add(id)
    visited[id] = true

    while (l < level) {
      val size = queue.size
      repeat(size) {
        val f = queue.removeFirst()
        for (ff in friends[f]) {
          if (!visited[ff]) {
            visited[ff] = true
            queue.add(ff)
          }
        }

        l++
      }
    }

    val wordsCount = HashMap<String, Int>()
    queue.forEach { f ->
      watchedVideos[f].forEach {
        val prev = wordsCount.get(it)
        wordsCount[it] = prev?.let { it + 1 } ?: 0
      }
    }

    return wordsCount.entries.sortedWith(
      compareBy<Map.Entry<String, Int>> { it.value }.thenBy { it.key }
    ).map { it.key }
  }
}

fun main() {
  fun test(
    watchedVideos: String, friends: String, id: Int, level: Int,
    expected: List<String>,
  ) {
    val wv: List<List<String>> =
      smatrix2d(watchedVideos).map { it.toList() }.toList()

    val result = Solution().watchedVideosByFriends(wv, matrix2d(friends), id, level)
    println("$ -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(
    """[["A","B"],["C"],["B","C"],["D"]]""",
    "[[1,2],[0,3],[0,3],[1,2]]",
    0, 1,
    listOf("B", "C")
  )

  test(
    """[["A","B"],["C"],["B","C"],["D"]]""",
    "[[1,2],[0,3],[0,3],[1,2]]",
    0, 2,
    listOf("D")
  )
}