package problem1002_2

class Solution {
    fun commonChars(A: Array<String>): List<String> {
        val result = mutableListOf<String>()
        val records = A.map {
            IntArray(26).apply {
                it.forEach {
                    this[it.code - 'a'.code]++
                }
            }
        }

        for (i in 0 until 26) {
            val count = records.minByOrNull { it[i] }!![i]
            for (j in 0 until count) {
                result.add((i + 'a'.code).toChar().toString())
            }
        }
        return result
    }
}

fun test(A: Array<String>) {
    println(A.toList().toString())
    println(Solution().commonChars(A))
    println()
}

test(arrayOf("bella", "label", "roller"))
test(arrayOf("cool", "lock", "cook"))