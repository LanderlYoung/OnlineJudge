package problem140

import equalsWithoutOrder
import shouldBeEqualToWithoutOrder

class Solution {
  // refer to 139
  fun wordBreak(s: String, wordDict: List<String>): List<String> {
    val words = wordDict.toHashSet()

    // memo[i] != null means s[0..<i] OK
    val memo = Array<ArrayList<List<String>>?>(s.length + 1) { null }
    memo[0] = arrayListOf(listOf(""))

    for (i in 1..s.length) {
      for (j in 0..<i) {
        val pre = memo[j]
        val tail = s.substring(j, i)
        if (
          pre != null && // [0..<j] is OK
          words.contains(tail) // [j..<i] is OK
        ) {
          // So. [0..i] is OK
          if (memo[i] == null) {
            memo[i] = ArrayList()
          }
          memo[i]!! += pre.map { it + tail }
        }
      }
    }

    return (memo[s.length] ?: emptyList()).map { list ->
      list.subList(1, list.size) // strip first ""
        .joinToString(" ")
    }
  }
}

fun main() {
  fun test(
    s: String, wordDict: List<String>,
    expected: List<String>,
  ) {
    val result = Solution().wordBreak(s, wordDict)
    println("${result} == ${expected} -> ${result equalsWithoutOrder expected}")
    result shouldBeEqualToWithoutOrder expected
  }

  test(
    "catsanddog",
    listOf("cat", "cats", "and", "sand", "dog"),
    listOf(
      "cats and dog",
      "cat sand dog"
    )
  )

  test(
    "pineapplepenapple",
    listOf(
      "apple", "pen", "applepen", "pine", "pineapple"
    ),
    listOf(
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    )
  )

  test("catsandog",
    listOf("cats", "dog", "sand", "and", "cat"),
    emptyList()
  )
}