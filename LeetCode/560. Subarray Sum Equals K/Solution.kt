class Solution {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var solution = 0
        for (i in 0 until nums.size) {
            var result = 0
            for (j in i until nums.size) {
                result += nums[j]
                if (result == k) {
                    solution++
                }
            }
        }
        return solution
    }
}