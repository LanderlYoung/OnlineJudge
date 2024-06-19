package problem198

import kotlin.math.max

// use brain power!!!!!!!!!
class Solution {
  fun rob(nums: IntArray): Int {
    if (nums.size == 1) {
      return nums[0]
    }

    // total money if nums[i] must be taken
    val moneyTake = IntArray(nums.size)
    moneyTake[0] = nums[0]

    for (i in 1..<nums.size) {
      // [x][x][x][i]
      //     v     v <- solution 1
      //  v        v <- solution 2
      // see which is bigger
      var money = nums[i]
      if (i - 2 >= 0) {
        money = max(money, moneyTake[i - 2] + nums[i])
      }
      if (i - 3 >= 0) {
        money = max(money, moneyTake[i - 3] + nums[i]
        )
      }
      moneyTake[i] = money
    }

    return max(moneyTake[nums.size - 1], moneyTake[nums.size - 2])
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