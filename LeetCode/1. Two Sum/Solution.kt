package problem1

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val len = nums.size
        for (i in 0 until len) {
            for (j in 0 until len) {
                if (i != j && nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf(0, 0)
    }
}