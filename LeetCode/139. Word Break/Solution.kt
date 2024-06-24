package problem139

import shouldBeEqualTo
import timedTest

// O(N^2) WTF: Time Limit Exceeded
class Solution {
  fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val root = buildTrierNode(wordDict)

    val recallStack = ArrayDeque<Int>()
    var node = root
    var i = 0
    while (true) {
      // reach end, and is full world, success
      if (i == s.length && node.isFullWord) {
        return true
      }

      // in the process, continue searching
      if (i < s.length) {
        val char = s[i]
        val child = node.peekChild(char)
        if (child != null) {
          if (child.isFullWord) {
            recallStack.addLast(i + 1)
          }
          node = child
          i++
          continue
        }
      }

      // failed search, try recall
      if (!recallStack.isEmpty()) {
        // reset state
        node = root
        i = recallStack.removeLast()
      } else {
        return false
      }
    }
  }

  private fun buildTrierNode(wordDict: List<String>): TrieNode {
    val root = TrieNode('!')
    wordDict.forEach { word ->
      var current = root
      word.forEach { char ->
        current = current.createChild(char)
      }
      current.isFullWord = true
    }

    return root
  }

  private data class TrieNode(
    val char: Char,
  ) {
    val next = Array<TrieNode?>(26) { null }

    var isFullWord: Boolean = false

    fun createChild(c: Char): TrieNode {
      val index = c - 'a'
      if (next[index] == null) {
        next[index] = TrieNode(c)
      }
      return next[index]!!
    }

    fun peekChild(c: Char): TrieNode? {
      return next[c - 'a']
    }
  }
}

fun main() = timedTest {
  fun test(
    input: String,
    wordDict: List<String>,
    expected: Boolean,
  ) {
    val result = Solution().wordBreak(input, wordDict)
    println("$input -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test("bb", listOf("a", "b", "bbb", "bbbb"), true)

  test("aaaaaaa",
    listOf("aaaa", "aa"),
    false)

  test(
    "helloworld",
    listOf("helloworlx", "hello", "world"),
    true
  )

  test("helloworldnihao",
    listOf("helloworldniha", "helloworld", "nihao"),
    true
  )

  test(
    "helloworld",
    listOf("helloworl", "hello", "world"),
    true
  )


  test(
    "leetcode",
    listOf("leet", "code"),
    true
  )

  test(
    "applepenapple",
    listOf("apple", "pen"),
    true
  )

  test(
    "catsandog",
    listOf("cats", "dog", "sand", "and", "cat"),
    false
  )

  test(
    "penpineappleapplepen",
    listOf("apple", "pen", "pine"),
    true
  )

  // TLE
  // test(
  //    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
  //   listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"),
  //   false
  // )
}