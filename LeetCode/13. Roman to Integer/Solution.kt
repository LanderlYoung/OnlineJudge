package problem13_1

class Solution {
  /**
   * Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000
   */
  val table = mapOf(
    'I' to 1,
    'V' to 5,
    'X' to 10,
    'L' to 50,
    'C' to 100,
    'D' to 500,
    'M' to 1000
  )

  fun romanToInt(s: String): Int {
    var result = 0

    var i = 0
    while (i < s.length) {
      val char = s[i]
      val value = table[char]!!

      if (i + 1 < s.length && value < table[s[i + 1]]!!) {
        result += table[s[i + 1]]!! - value
        i += 2

      } else {
        result += value
        i++
      }
    }

    return result
  }
}

// test

fun test(
  // input
  s: String,
  // expected
  expected: Int,
) {

  println("${Solution().romanToInt(s)} == ${expected}")
}

fun main() {
  test("III", 3)
  test("LVIII", 58)
  test("MCMXCIV", 1994)
}