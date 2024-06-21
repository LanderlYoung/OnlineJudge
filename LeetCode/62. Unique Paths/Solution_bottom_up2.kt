package problem62_bottom_up

class Solution {

  fun uniquePaths(m: Int, n: Int): Int {
    up = Array(m) { IntArray(n) }
    up[0][0] = 1
    var i = 0
    var j = 0
    while (true) {
      val goI = i + 1 < m
      val goJ = j + 1 < n

      if (goI) {
        for (newI in i + 1..<m) {
          fill(newI, j)
        }
      }
      if (goJ) {
        for (newJ in j + 1..<n) {
          fill(i, newJ)
        }
      }

      if (goI) i++
      if (goJ) j++
      if (goI && goJ) {
        fill(i, j)
      }
      if (!goI && !goJ) {
        break
      }
    }

    return up[m - 1][n - 1]
  }

  private fun fill(i: Int, j: Int) {
    var result = 0
    if (i >= 1) {
      result += up[i - 1][j]
    }
    if (j >= 1) {
      result += up[i][j - 1]
    }

    up[i][j] = result
  }

  lateinit var up: Array<IntArray>
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