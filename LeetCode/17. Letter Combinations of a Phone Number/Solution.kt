package problem17

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

  fun letterCombinations(digits: String): List<String> {
    return letterCombinationsRecursive(digits)
  }

  private fun letterCombinationsRecursive(digits: CharSequence): List<String> {
    if (digits.isEmpty()) {
      return emptyList()
    }

    val chars = map[digits.first()]!!
    if (digits.length == 1) {
      return chars.map { it.toString() }
    }

    // recursive
    val combos = letterCombinationsRecursive(digits.subSequence(1, digits.length))

    return chars.flatMap { char ->
      combos.map { combo -> char + combo }
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