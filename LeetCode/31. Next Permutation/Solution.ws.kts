package problem31

class Solution {
    fun nextPermutation(nums: IntArray): Unit {
        if (nums.size == 1) return

        var offset = nums.size - 1
        var max = nums[offset]

        while (true) {
            offset--
            if (offset < 0) {
                // rearrange needed...
            }
            val cur = nums[offset]
            if (cur < max) {
                // yes
            } else {
                max = cur
            }
        }
    }

    private fun IntArray.hasCarry(offset: Int) =
        this[offset] >= this[offset + 1]

    private fun IntArray.swap(from: Int, to: Int) {
        val v = this[from]
        this[from] = this[to]
        this[to] = v
    }

    // 1234 -> 1243 -> 1423
    // 1243 1324

}

run {
    val arr = intArrayOf(1, 3, 2)
    Solution().nextPermutation(arr)
    arr.toList()
}

run {
    val arr = intArrayOf(2, 3, 1)
    Solution().nextPermutation(arr)
    arr.toList()
}