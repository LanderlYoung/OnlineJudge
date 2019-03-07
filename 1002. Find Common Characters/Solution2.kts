class Solution {
    fun commonChars(A: Array<String>): List<String> {
        val result = mutableListOf<String>()
        val records = A.map {
            IntArray(26).apply {
                it.forEach {
                    this[it.toInt() - 'a'.toInt()]++
                }
            }
        }

        for (i in 0 until 26) {
            val count = records.minBy { it[i] }!![i]
            for (j in 0 until count) {
                result.add((i + 'a'.toInt()).toChar().toString())
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