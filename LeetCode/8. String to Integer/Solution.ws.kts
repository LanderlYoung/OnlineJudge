package problem8

class Solution {
  fun myAtoi(s: String): Int {
    var result: Long = 0L
    var sign = 1

    var index = 0

    // leading whitespace
    while (index < s.length && s[index] == ' ') index++

    // sign
    if (index < s.length ) {
      val signChar = s[index]
      if (signChar == '-') {
        sign = -1
        index++
      } else if (signChar == '+') {
        index++
      }
    }

    // digitals
    while (index < s.length) {
      val value = readInt(s, index)
      if (value < 0) {
        break
      }

      result = result * 10 + value

      // round
      if (sign == 1 && result > Int.MAX_VALUE) {
        return Int.MAX_VALUE
      } else if (sign == -1 && -result < Int.MIN_VALUE) {
        return Int.MIN_VALUE
      }

      index++
    }

    return result.toInt() * sign
  }

  fun readInt(s: String, i: Int): Int {
    val char = s[i]
    if (char >= '0' && char <= '9') {
      return char.code - '0'.code
    }
    return -1
  }
}


fun test(args: String) {
  println("[$args] [${Solution().myAtoi(args)}]")
}

run {
  println('0'.code)
  println('9'.code)
  println("---------------")
  val x = '0'.code
  val y = '9'.code

  listOf(
    "42",
    " -042",
    "1337c0d3",
    "0-1",
    "words and 987",
    "-91283472332", // 期望结果:-2147483648
  ).forEach(::test)
}

