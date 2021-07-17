package problem46

/*
 * ```
 * Author: taylorcyang@tencent.com
 * Date:   2021-07-16
 * Time:   13:11
 * Life with Passion, Code with Creativity.
 * ```
 */

class Solution {
    private lateinit var result: MutableList<MutableList<Int>>

    private fun factorial(n: Int): Int = if (n == 1) 1 else n * factorial(n - 1)

    fun permute(nums: IntArray): List<List<Int>> {
        result = ArrayList(factorial(nums.size))
        permute(nums, 0)
        return result
    }

    private fun permute(nums: IntArray, offset: Int) {
        fun list() = ArrayList<Int>(nums.size)
        when (nums.size - offset) {
            0 -> return
            1 -> {
                result.add(
                    list().also { it.add(nums[offset]) }
                )
                return
            }
            2 -> {
                result.add(
                    list().also {
                        it.add(nums[offset])
                        it.add(nums[offset + 1])
                    })
                result.add(
                    list().also {
                        it.add(nums[offset + 1])
                        it.add(nums[offset])
                    }
                )
                return
            }
        }
        for (index in offset until nums.size) {
            val head = nums[offset]
            val old = nums[index]
            nums[index] = head

            val beforeIndex = result.size
            permute(nums, offset + 1)
            for (i in beforeIndex until result.size) {
                result[i].add(0, old)
            }
            nums[index] = old
        }
    }
}

fun main() {
    println(Solution().permute(intArrayOf(1, 2, 3)))
}