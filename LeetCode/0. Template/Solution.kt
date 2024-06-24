package problem0

import shouldBeEqualTo

class Solution {
    fun twoSum(input: Int): Int {
        return input
    }
}

fun main() {
    fun test(
        input: Int,
        expected: Int,
    ) {
        val result = Solution().twoSum(input)
        println("$input -> ${result} == ${expected} -> ${result == expected}")
        result shouldBeEqualTo expected
    }


}