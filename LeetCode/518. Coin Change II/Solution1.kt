package problem518

import shouldBeEqualTo

class Solution {
  fun change(amount: Int, coins: IntArray): Int {
    if (amount == 0) return 1

    // 1. define sub-problem
    // f(i, j) -> use coins[0..<i] to change j money
    // result = f(coins.num, amount)
    // edge: f(i, 0) -> 1; f(0, j) -> 0; f(0, 0) -> 1
    val dp = Array(coins.size + 1) { IntArray(amount + 1) }
    for (i in dp.indices) {
      dp[i][0] = 1
    }

    for (i in 1..coins.size) {
      for (j in 1..amount) {
        // 2. recursion formula
        // f(i, j) ->
        val coin = coins[i - 1]

        var sum = 0
        var left = j - coin
        while (left >= 0) {
          // use x new coins, how much left to change
          // change the `left` money with coins without [i - 1]
          if (left == 0) {
            sum++
          } else {
            var pre = i - 1
            while (pre > 0) {
              sum += dp[pre][left]
              pre--
            }
          }

          left -= coin
        }
        dp[i][j] = sum
      }
    }

    var sum = 0
    for (i in 1..coins.size) {
      sum += dp[i][amount]
    }
    return sum
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