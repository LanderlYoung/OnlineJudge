package problem1_3

// using binary search
class Solution {
  fun twoSum(nums: IntArray, target: Int): IntArray {
    val sorted = nums.mapIndexed { index, i -> i to index }
      .sortedBy { it.first }

    for (i in 1..<sorted.size) {
      val x = target - sorted[i].first

      // binary search previous
      val found = binarySearch(i, sorted, x)
      if (found != -1) {
        return intArrayOf(sorted[found].second, sorted[i].second)
      }
    }

    return intArrayOf(0, 0)
  }

  private fun binarySearch(size: Int, nums: List<Pair<Int, Int>>, x: Int): Int {
    var left = 0
    var right = size - 1
    while (left <= right) {
      val mid = (left + right) / 2

      if (nums[mid].first == x) {
        return mid
      } else if (nums[mid].first > x) {
        right = mid - 1
      } else {
        left = mid + 1
      }
    }

    return -1
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