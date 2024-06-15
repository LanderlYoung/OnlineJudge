package problem1_4

// two pointer
class Solution {
  fun twoSum(nums: IntArray, target: Int): IntArray {
    val sorted = nums.mapIndexed { index, i -> i to index }
      .sortedBy { it.first }

    var left = 0
    var right = nums.size - 1

    while (left < right) {
      val sum = sorted[left].first + sorted[right].first
      if (sum == target) {
        return intArrayOf(sorted[left].second, sorted[right].second)
      } else if (sum < target) {
        left++
      } else {
        right--
      }
    }
    return intArrayOf(0, 0)
  }
}

fun test(
  // input
  nums: IntArray, target: Int,
  // expected
  expected: IntArray,
) {

  val result = Solution().twoSum(nums, target)
  println("(${nums.contentToString()}, $target) -> ${result.contentToString()} == ${expected.contentToString()} -> " +
      "${result.contentEquals(expected)}")
}

fun main() {
  test(
    intArrayOf(3, 2, 4), 6,
    intArrayOf(1, 2)
  )

  test(
    intArrayOf(3, 3), 6,
    intArrayOf(0, 1)
  )


  test(
    intArrayOf(2, 7, 11, 15), 9,
    intArrayOf(0, 1)
  )


}