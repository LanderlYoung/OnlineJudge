package problem62_bottom_up2

class Solution {
  fun uniquePaths(m: Int, n: Int): Int {
    val up = Array(m) { IntArray(n) }

    for (i in 0..<m) {
      for (j in 0..<n) {
        if (i == 0 || j == 0) {
          up[i][j] = 1
        } else {
          up[i][j] = up[i - 1][j] + up[i][j - 1]
        }
      }
    }
    return up[m - 1][n - 1]
  }
}

// test
fun test(
  // input
  m: Int,
  n: Int,

  // expected
  expected: Int,
) {
  val result = Solution().uniquePaths(m, n)
  println("($m, $n) -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  test(3, 7, 28)
  test(3, 2, 3)
}