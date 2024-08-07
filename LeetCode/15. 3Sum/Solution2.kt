package problem15_2

// hash map
class Solution {
  fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    val numFirstAppearedIndex = HashMap<Int, Int>()
    nums.forEachIndexed { index, num ->
      if (!numFirstAppearedIndex.contains(num)) {
        numFirstAppearedIndex[num] = index
      }
    }

    val result = mutableSetOf<List<Int>>()

    for (i in 2..<nums.size) {
      for (j in 1..<i) {
          val x = -(nums[i] + nums[j])
          val index = numFirstAppearedIndex[x]
          if (index != null && index < j) {
            result += listOf(nums[index], nums[j], nums[i])
          }
        }
    }

    return result.toList()
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