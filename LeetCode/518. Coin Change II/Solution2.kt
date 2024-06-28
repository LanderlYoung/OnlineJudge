package problem518_4

import shouldBeEqualTo

// optimized solution 2
class Solution {
  // 1. define sub-problem
  // f(c, n) -> use coins[0..<c] to change n money
  //    whether coin [c-1] is selected or not
  // result = f(coins.num, amount)
  //
  // edge: f(c, 0) -> 1; f(0, n) -> 0; f(0, 0) -> 1
  //
  fun change(amount: Int, coins: IntArray): Int {
    if (amount == 0) return 1

    val dp = Array(coins.size + 1) { IntArray(amount + 1) }
    for (i in 0..coins.size) {
      dp[i][0] = 1
    }

    for (c in 1..coins.size) {
      for (n in 1..amount) {
        // 2. recursion formula
        // f(c, n) -> take i count or n, f(c-1, n-i*coin)
        var count = 0

        // a. don't use new coin to change n
        count += dp[c - 1][n]

        // b. use coin to change n (and must use coin)
        // n - coins[c] -> to make sure one coin is used
        val valueLeftUsingOneNewCoin =n - coins[c - 1]
        if (valueLeftUsingOneNewCoin >= 0) {
          count += dp[c][valueLeftUsingOneNewCoin]
        }

        dp[c][n] = count
      }
    }

    // Step 4. space optimize
    // 1. Noticed that only previous row is used (c - 1), We can use `previousRow` array.
    // 2. Further investigating, we can found the (c - 1) row only use n-th element, we can reuse only one row!

    return dp[coins.size][amount]
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