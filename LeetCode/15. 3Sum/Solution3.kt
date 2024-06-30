package problem15_3

// binary search
class Solution {
  fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    val result = mutableSetOf<List<Int>>()

    for (i in 2..<nums.size) {
      for (j in 1..<i) {
        val x = -(nums[i] + nums[j])
        val index = binarySearch(nums, j, x)
        if (index != -1) {
          result += listOf(nums[index], nums[j], nums[i])
        }
      }
    }

    return result.toList()
  }

  private fun binarySearch(nums: IntArray, size: Int, value: Int): Int {
    var left = 0
    var right = size - 1
    while (left <= right) {
      val mid = (left + right) / 2

      if (nums[mid] == value) {
        return mid
      } else if (nums[mid] > value) {
        right = mid - 1
      } else {
        left = mid + 1
      }
    }

    return -1
  }
}

// test
fun test(
  // input
  input: IntArray,

  // expected
  expected: List<List<Int>>,
) {

  val result = Solution().threeSum(input)
  val eq = result.size == expected.size && result.toSet() == expected.toSet()
  println("${input.contentToString()} -> ${result} == ${expected} -> ${eq}")
}

fun main() {
  test(
    intArrayOf(0, 0, 0),
    listOf(
      listOf(0, 0, 0)
    )
  )

  test(
    intArrayOf(-1, 0, 1, 2, -1, -4),
    listOf(
      listOf(-1, -1, 2),
      listOf(-1, 0, 1)
    )
  )
}