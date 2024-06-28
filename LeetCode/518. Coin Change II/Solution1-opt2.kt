package problem518_3

import shouldBeEqualTo

// same as Solution2, change Pair to long
class Solution {
  fun change(amount: Int, coins: IntArray): Int {
    // 1. define sub-problem
    // f(c, n) -> Pair<
    //    use coins[0..<c] to change n money AND coin [c-1] must be selected,
    //    ... that cion[c-1] is not selected.
    // >
    // result = f(coins.num, amount).first + f(coins.num, amount).second
    //
    // edge: f(c, 0) -> <0, 1>; f(0, n) -> 0; f(0, 0) -> 1
    val dp = Array(coins.size + 1) { LongArray(amount + 1) }
    for (i in dp.indices) {
      dp[i][0] = makeLong(0, 1)// 0 to 1
    }
    for (i in 0..amount) {
      dp[0][i] = 0 // 0 to 0
    }

    for (c in 1..coins.size) {
      for (n in 1..amount) {
        // 2. recursion formula
        val denomination = coins[c - 1]

        // don't use coin[c - 1]
        val unselectedSum = dp[c - 1][n].sumPart()

        // use coin[c - 1] any count
        var selectedSum = 0
        var left = n - denomination
        while (left >= 0) {
          if (left == 0) {
            selectedSum++
          } else {
            // use `previous coins` to change `left` money
            selectedSum += dp[c - 1][left].sumPart()
          }
          left -= denomination
        }
        dp[c][n] = makeLong(selectedSum, unselectedSum)
      }
    }
    return dp[coins.size][amount].sumPart()
  }

  private fun makeLong(i1: Int, i2: Int): Long {
    return ((i1.toULong() shl 32) or i2.toULong()).toLong()
  }

  private fun Long.sumPart(): Int {
    return (toULong() + (toULong() shr 32)).toInt()
  }
}

fun main() {
  fun test(
    amount: Int,
    coins: IntArray,
    expected: Int,
  ) {
    val result = Solution().change(amount, coins)
    println("$amount - ${coins.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }
  test(0, intArrayOf(1, 2, 5), 1)
  test(5, intArrayOf(1, 2, 5), 4)
  test(3, intArrayOf(2), 0)
  test(10, intArrayOf(10), 1)
}