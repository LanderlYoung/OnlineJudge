package problem62

class Solution {
  /**
   * step 1: define sub problem
   *  Let `f(i, j)` means how many unique paths there is to move from `[0, 0]` `->` `[i - 1, i - 1]`.
   *  So the final answer should be `f(m, n)`
   *
   * step 2: recursion problem
   * ```
   * |---------------------> i
   * |   |   |   | s | → |
   * |   |   |   | s | ↓ |
   * |   |   |   | s | ↓ |
   * | s | s | s | s | ↓ |
   * | → | → | → | → | X |
   * |--------------------
   * ↓
   * j
   * ```
   *  In order to move to f(i, j), we assume sub problems are solved!
   *  So the problem is to find out how many ways we have to move from sub-matrix to f(i, j)
   *  We two surrounding points
   *    1. f(i - 1, j) going right
   *    2. f(i, j - 1) going down
   *      So f(i, j) = f(i -1, j) + f(i, j - 1)
   *    Also, mind edge condition, if i-1/j-1 < 0 then ignore it
   *
   * step 3: choose calculate direction
   *   for 2D matrix, how can we spread from one point to the end?
   *   method 1: top down
   *      It's easy, we don't need to choose how to going from inner to out.
   *   method 1: bottom up
   *      Direction matters. from (i, j) -> (i + 1, j + 1)
   *      step 1 covers: (i + 1, 0..j)
   *      step 2 covers: (0..i, j + 1)
   *      step 3 cover: (i + 1, j + 1)
   *
   * step 4: optimize space usage
   *   TODO
   */
  fun uniquePaths(m: Int, n: Int): Int {
    // let's go top_down first
    up = Array(m) { IntArray(n) }
    up[0][0] = 1

    return f(m - 1, n - 1)
  }

  lateinit var up: Array<IntArray>

  fun f(i: Int, j: Int): Int {
    if (i < 0 || j < 0) return 0

    if (up[i][j] == 0) {
      up[i][j] = f(i - 1, j) + f(i, j - 1)
    }

    return up[i][j]
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