class Solution {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var matches = 0

        var start = 0
        var i = 0
        var sum = 0
        while (i < nums.size) {
            sum += nums[i]

            if (sum == k) {
                matches++
                sum -= nums[start]
                start++
                i++
                continue
            }
            val startNum = nums[start]
            if (startNum >= 0) {
                when {
                    sum > k -> {
                        sum -= nums[start]
                        start++
                        if (start >= i) {
                            // reset
                            i = start
                            sum = 0
                        }
                    }
                    else -> i++
                }
            } else {

            }
        }
        return matches
    }
}

// test
println(Solution().subarraySum(intArrayOf(1), 0))
