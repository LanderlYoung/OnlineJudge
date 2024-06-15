package problem15_4

// doual-pointer
class Solution {
  fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    val result = mutableListOf<List<Int>>()

    var i = 2
    while (i < nums.size) {
      val v = nums[i]
      while (i + 1 < nums.size && nums[i + 1] == v) {
        i++
      }

      val target = -v
      twoSum(nums, i, target, result)

      i++
    }

    return result.toList()
  }

  private fun twoSum(
    nums: IntArray, size: Int,
    target: Int, result: MutableCollection<List<Int>>,
  ) {
    var left = 0
    var right = size - 1

    while (left < right) {
      val sum = nums[left] + nums[right]

      if (sum == target) {
        result.add(listOf(nums[left], nums[right], nums[size]))
      }
      if (sum <= target) {
        val lv = nums[left]

        while (left < right && nums[left] == lv) left++
      } else {
        val rv = nums[right]

        while (right > left && nums[right] == rv) right--
      }
    }
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
  test(intArrayOf(-2, -1, -1, 2), listOf(listOf(-1, -1, 2)))
  test(intArrayOf(-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6),
    listOf(listOf(-4, -2, 6),
      listOf(-4, 0, 4),
      listOf(-4, 1, 3),
      listOf(-4, 2, 2),
      listOf(-2, -2, 4),
      listOf(-2, 0, 2))
  )

  test(intArrayOf(-2, 0, 1, 1, 2), listOf(listOf(-2, 0, 2), listOf(-2, 1, 1)))

  test(intArrayOf(0, 0, 0), listOf(listOf(0, 0, 0)))

  test(intArrayOf(0, 0, 0, 0, 0, 0), listOf(listOf(0, 0, 0)))

  test(intArrayOf(-1, 0, 1, 2, -1, -4), listOf(listOf(-1, -1, 2), listOf(-1, 0, 1)))
}