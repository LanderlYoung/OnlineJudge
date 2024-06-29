package problem17_2

class Solution {
  companion object {
    val map: Array<String> = arrayOf(
      "", // 0
      "", // 1
      "abc",
      "def",
      "ghi",
      "jkl",
      "mno",
      "pqrs",
      "tuv",
      "wxyz"
    )
  }

  private val results = mutableListOf<String>()
  private val sb = StringBuilder()

  fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return emptyList()

    letterCombinationsRecursive(digits)
    return results
  }

  private fun letterCombinationsRecursive(digits: CharSequence) {
    if (digits.isEmpty()) {
      results.add(sb.toString())
      return
    }

    val chars = map[digits.first() - '0']
    chars.forEach { char ->
      sb.append(char)
      letterCombinationsRecursive(digits.subSequence(1, digits.length))
      // delete char
      sb.setLength(sb.length - 1)
    }
  }
}

// test
fun test(
  // input
  input: String,

  // expected
  expected: List<String>,
) {

  val result = Solution().letterCombinations(input)
  println("$input -> ${result} == ${expected} -> ${result.size == expected.size && result.toSet() == expected.toSet()}")
}

fun main() {
  test("", emptyList())

  test("2", listOf("a", "b", "c"))

  test("23", listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))

}