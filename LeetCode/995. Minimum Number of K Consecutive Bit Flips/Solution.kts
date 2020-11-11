class Solution {
    fun minKBitFlips(A: IntArray, K: Int): Int {
        var count = 0
        for (i in A.indices) {
            if (A[i] == 1) {
                continue
            }
            // try flip
            if (i + K > A.size) {
                return -1
            }
            // flip
            for (j in i until (i + K)) {
                A[j] = A[j] xor 1
            }
            count++
        }
        return count
    }
}

println(Solution().minKBitFlips(intArrayOf(0, 0, 0, 1, 0, 1, 1, 0), 3))