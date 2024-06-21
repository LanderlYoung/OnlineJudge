package problem0

class Solution {
    fun twoSum(input: Int): Int {
        return input
    }
}

fun main() {
    // test
    fun test(
        // input
        input: Int,

        // expected
        expected: Int,
    ) {

        val result = Solution().twoSum(input)
        println("$input -> ${result} == ${expected} -> ${result == expected}")
    }


}