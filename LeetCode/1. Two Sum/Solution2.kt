package problem1_2

// using HashMap
class Solution {
  fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()

    repeat(nums.size) { i ->
      val x = target - nums[i]
      // only need to find value prev to i
      val index = map[x]
      if (index != null && index != i) {
        return intArrayOf(i, index)
      }
      map[nums[i]] = i
    }

    return intArrayOf(0, 0)
  }
}