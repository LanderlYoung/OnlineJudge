package problem14

class Solution {
  fun longestCommonPrefix(strs: Array<String>) = buildString {
    if (strs.size == 1) {
      return strs[0]
    }

   strs.sort()

    var i = 0
    while (true) {
      for (j in 0..<(strs.size - 1)) {
        if (i >= strs[j].length ||
          i >= strs[j + 1].length ||
          strs[j][i] != strs[j + 1][i]
        ) {
          return@buildString
        }
      }
      append(strs[0][i])
      i++
    }
  }
}

// test

fun test(
  // input
  input: Array<String>,

  // expected
  expected: String,
) {

  val result = Solution().longestCommonPrefix(input)
  println("${input.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  test(
    arrayOf("flower"),
    "flower"
  )

  test(
    arrayOf(""),
    ""
  )

  test(
    arrayOf("flower", "flow", "flight"),
    "fl"
  )

  test(
    arrayOf("dog", "racecar", "car"),
    ""
  )
}