package problem393

import shouldBeEqualTo

class Solution {
  fun validUtf8(data: IntArray): Boolean {
    var i = 0
    while (i < data.size) {
      val l = popCount(data[i])
      when (l) {
        0 -> {}
        2, 3, 4 -> {
          val expectCount = l - 1

          repeat(expectCount) {
            i++
            if (i >= data.size || popCount(data[i]) != 1) {
              return false
            }
          }
        }

        else -> return false
      }

      i++
    }

    return true
  }

  // leading 1 count
  fun popCount(i: Int): Int {
    var shift = 7
    while (shift >= 0) {
      if ((i ushr shift) and 1 == 0) {
        break
      }
      shift--
    }
    return 7 - shift
  }
}

// test
fun test(
  // input
  input: IntArray,

  // expected
  expected: Boolean,
) {

  val result = Solution().validUtf8(input)
  println("$input -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  Solution().popCount(0b0100_0000) shouldBeEqualTo 0
  Solution().popCount(0b1000_0000) shouldBeEqualTo 1
  Solution().popCount(0b1100_0000) shouldBeEqualTo 2
  Solution().popCount(0b1110_0000) shouldBeEqualTo 3
  Solution().popCount(0b1111_1111) shouldBeEqualTo 8

  test(
    intArrayOf(197, 130, 1),
    true
  )

  test(
    intArrayOf(235, 140, 4),
    false
  )
}