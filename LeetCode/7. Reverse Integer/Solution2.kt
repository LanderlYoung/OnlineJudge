package problem7_2

class Solution {
  companion object {
    val overflowMin = Int.MIN_VALUE / 10
    val overflowMax = Int.MAX_VALUE / 10
  }

  fun reverse(x: Int): Int {
    var i = x
    var r = 0
    while (i != 0) {
      val v = i % 10
      if (r > overflowMax || r < overflowMin) {
        return 0
      }
      r = 10 * r + v

      i /= 10
    }

    return r
  }
}

// test
fun test(
  // input
  i: Int,
  // expected
  expected: Int,
) {

  val result = Solution().reverse(i)
  println("$i -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  test(-123, -321)
  test(123, 321)
  test(100, 1)
  test(-100, -1)
  test(0, 0)
  test(-1, -1)
  test(1234567, 7654321)
  test(-1234567, -7654321)
  test(Int.MAX_VALUE, 0)
}