package problem17_1

class Solution {
  companion object {
    val map: Map<Char, List<Char>> = mapOf(
      '2' to "abc",
      '3' to "def",
      '4' to "ghi",
      '5' to "jkl",
      '6' to "mno",
      '7' to "pqrs",
      '8' to "tuv",
      '9' to "wxyz"
    ).mapValues { it.value.map { it } }
  }

  private val results = mutableListOf<String>()

  fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return emptyList()

    letterCombinationsRecursive(StringBuilder(), digits)
    return results
  }

  private fun letterCombinationsRecursive(prevSb: StringBuilder, digits: CharSequence) {
    if (digits.isEmpty()) {
      results.add(prevSb.toString())
      return
    }

    val chars = map[digits.first()]!!
    chars.forEachIndexed { index, char ->
      val sb =
        if (index == chars.size - 1) {
          prevSb
        } else {
          StringBuilder(prevSb)
        }
      sb.append(char)

      letterCombinationsRecursive(sb, digits.subSequence(1, digits.length))
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