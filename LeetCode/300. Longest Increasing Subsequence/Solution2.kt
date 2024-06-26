package problem300_2

import shouldBeEqualTo

class Solution {
  fun lengthOfLIS(nums: IntArray): Int {
    val sequence = IntArray(nums.size)
    var len = 1
    sequence[0] = nums[0]

    for (i in 1..<nums.size) {
      val value = nums[i]

      if (value > sequence[len - 1]) {
        // enlarge the sequence
        sequence[len++] = value
      } else {
        // 1256 <- 3
        // replace the 5 to 3

        // find first index that > value
        val index = sequence.binarySearch(value, 0, len)
        if (index < 0) {
          val insertPosition = -index - 1
          sequence[insertPosition] = value
        }
      }
    }

    return len
  }
}

fun main() {
  fun test(
    vararg input: Int,
    expected: Int,
  ) {
    val result = Solution().lengthOfLIS(input)
    println("${input.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(
    0, 1, 0, 3, 2, 3,
    expected = 4
  )

  test(
    10, 9, 2, 5, 3, 7, 101, 18,
    expected = 4
  )

  test(
    7, 7, 7, 7, 7, 7, 7,
    expected = 1
  )
}