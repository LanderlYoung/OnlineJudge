package problem139_2

import shouldBeEqualTo

// lets try DP???
class Solution {
  /**
   * Step 1: define sub-problem
   *  f(i) means can s[0..<i] be a Word break
   *
   * Step 2: define recursion formula
   *  f(i + 1) = for i in 0..i+1, any f(j) && s[j ..< i+1] in wordDict
   */
  fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val words = wordDict.toHashSet()

    val memo = BooleanArray(s.length + 1)
    memo[0] = true

    for (i in 1..s.length) {
      for (j in 0..i) {

        if (
          memo[j] &&  // [0..<j] is Word Break
          words.contains(s.substring(j, i)) // [j..<i] is Word Bread
          ) {
          // so [0..<i] is Word Break
          memo[i] = true
          break
        }
      }
    }

    return memo[s.length]
  }
}

fun main() {
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
    "catsanddog",
    listOf("cats", "dog", "sand", "cat"),
    true
  )

  test(
    "penpineappleapplepen",
    listOf("apple", "pen", "pine"),
    true
  )

  test(
    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
    listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"),
    false
  )
}