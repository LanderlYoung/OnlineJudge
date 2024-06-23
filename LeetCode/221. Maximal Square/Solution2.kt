package problem221_2

import kotlin.math.max

class Solution {
    /**
     * step 1: define sub-problem
     *   f(i, j) == N -> means when we use f(i, j) as right-bottom corner, this is a N sized Square contains only 1
     *   so the final answer should be max(min(f(i,j) ^ 2)
     *
     * step 2: define recursion formula
     *   f(i, j) = if(matrix[i][j] == '0') [0, 0] else {
     *          // so the question ahead is to determine how big a square [i, j] can make
     *          // we should do some geometry
     *       else (0, 0)
     *   }
     *
     */
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val dp = Array(matrix.size) { IntArray(matrix[0].size) }

        var maxSquare = 0
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] != '1') {
                    dp[i][j] = 0
                } else {
                    val top = dp.getOrNull(i - 1)?.getOrNull(j) ?: 0
                    val left = dp.getOrNull(i)?.getOrNull(j - 1) ?: 0
                    val leftTop = dp.getOrNull(i - 1)?.getOrNull(j - 1) ?: 0

                    val newSquareSize = minOf(top, left, leftTop) + 1

                    dp[i][j] = newSquareSize
                    maxSquare = max(maxSquare, newSquareSize)
                }
            }
        }

        return maxSquare * maxSquare
    }
}

fun main() {
    // test
    fun test(
        // input
        matrix: Array<String>,

        // expected
        expected: Int,
    ) {

        val result = Solution().maximalSquare(matrix.map { it.toCharArray() }.toTypedArray())
        println("${matrix.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
    }

    test(
        arrayOf(
            "10100",
            "10111",
            "11111",
            "10010"
        ),
        4
    )

    test(
        arrayOf(
            "01",
            "10"
        ),
        1
    )
}