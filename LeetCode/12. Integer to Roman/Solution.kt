package problem12_1

import java.lang.StringBuilder

class Solution {

  fun intToRoman(num: Int): String = buildString {
    var value = num
    repeat(num / 1000) { append("M") }
    value %= 1000

    appendRoman(value / 100, 100)
    value %= 100

    appendRoman(value / 10, 10)
    value %= 10

    appendRoman(value, 1)
  }

  private fun StringBuilder.appendRoman(value: Int, exponents: Int) {
    val one = when (exponents) {
      100 -> 'C'
      10 -> 'X'
      1 -> 'I'
      else -> ""
    }

    val five = when (exponents) {
      100 -> 'D'
      10 -> 'L'
      1 -> 'V'
      else -> ""
    }

    val ten = when (exponents) {
      100 -> 'M'
      10 -> 'C'
      1 -> 'X'
      else -> error("")
    }

    if (value == 5) {
      append(five)
    } else if (value == 4) {
      append(one).append(five)
    } else if (value == 9) {
      append(one).append(ten)
    } else if (value > 5) {
      append(five)
      repeat(value - 5) { append(one) }
    } else {
      repeat(value) { append(one) }
    }
  }
}

// test

fun test(
  // input
  input: Int,
  // expected
  expected: String,
) {

  println("$input -> ${Solution().intToRoman(input)} == ${expected}")
}

fun main() {
  test(58, "LVIII")
  test(1994, "MCMXCIV")
  test(3, "III")
}