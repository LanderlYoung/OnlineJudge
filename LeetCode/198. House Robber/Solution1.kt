package problem198_1

import kotlin.math.max

// use the solver https://leetcode.cn/problems/house-robber/solutions/138131/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap
class Solution {
  fun rob(nums: IntArray): Int {


    /// step 1: define sub problem
    // let's say f(i) means how much you can rob in nums[0..<i] (exclusive)
    // so the answer should be f(nums.size - 1)

    /// step 2: determine recursion formula
    // for f(i), we have two solutions
    //    -- 1. don't take f(i), so it's f(i - 1)
    //    -- 2. take f(i), so it's f(i - 2) + nums[i - 1]
    // either way, we take the bigger one
    // so the formula is: f(i) = max(f(i - 1), f(i - 2) + nums[i - 1])

    /// step 2.1: be careful about edge condition
    //  - f(0) = 0
    //  - f(1) = num[0]

    /// step 3: choose calculate direction
    //  1. bottom up
    //     start from basic sub problem, enlarge the scope
    //  2. top down
    //     assume the sub problems are solved, directly calculate the final (large scope problem)
    //     so, usually it's a recursive pattern, to speed up (avoid unnecessary duplicate calls),
    //     a memo data structure is need.

    /// step 4: optimize space usage
    //    from the recursion formula :f(i) = max(f(i - 1), f(i - 2) + nums[i - 1])
    //    we noticed that only preview TWO result are used, the more previously items
    //    are not really necessary.
    //    So we change from using an array to using two values
    //
    //    int prev1, prev2;
    //    for (i in 2..nums.size) {
    //      money = max(prev1, prev2 + nums[i - 1]);
    //      prev2 = prev1;
    //      prev1 = money;
    //    }
    //    return prev1;
    //
    //    see Solution2.kt

    if (nums.size == 1) {
      return nums[0]
    }

    // total money if nums[i] must be taken
    val money = IntArray(nums.size + 1)
    money[0] = 0
    money[1] = nums[1]

    for (i in 2..nums.size) {
      money[i] = max(money[i - 1], money[i - 2] + nums[i - 1])
    }

    return money[nums.size]

    /// step 4: optimize space usage
    //    from the recursion formula :f(i) = max(f(i - 1), f(i - 2) + nums[i - 1])
    //    we noticed that only preview TWO result are used, the more previously items
    //    are not really necessary.
    //    So we change from using an array to using two values
    //
    //    int prev1, prev2;
    //    for (i in 2..nums.size) {
    //      money = max(prev1, prev2 + nums[i - 1]);
    //      prev2 = prev1;
    //      prev1 = money;
    //    }
    //    return prev1;
    //
    //    see Solution2.kt
  }
}

// test
fun test(
  // input
  vararg input: Int,

  // expected
  expected: Int,
) {

  val result = Solution().rob(input)
  println("${input.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  test(2, 1, 1, 2, expected = 4)
  test(1, 2, 3, 1, expected = 4)
  test(2, 3, 2, expected = 4)
  test(2, 4, 3, expected = 5)
  test(2, 4, 3, 2, expected = 6)
  test(1, 2, 3, 1, expected = 4)
  test(2, 7, 9, 3, 1, expected = 12)
}