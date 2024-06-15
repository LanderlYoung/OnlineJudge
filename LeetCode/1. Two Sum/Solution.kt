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
        intArrayOf(2, 7, 11, 15), 9,
        intArrayOf(0, 1)
    )

    test(
        intArrayOf(3, 2, 4), 6,
        intArrayOf(1, 2)
    )

    test(
        intArrayOf(3, 3), 6,
        intArrayOf(0, 1)
    )
}