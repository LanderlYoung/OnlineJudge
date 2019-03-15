class Solution {
    fun commonChars(A: Array<String>): List<String> {
        val a = A.map { it.toList().sorted() }
            .sortedBy { it.size }
        val indexes = IntArray(A.size)
        val result = mutableListOf<String>()

        loop@
        while (indexes.first() < a.first().size) {
            val char = a.first()[indexes.first()]
            var found = 1
            for (i in 1 until indexes.size) {
                val arr = a[i]
                var j = indexes[i]
                while (j < arr.size && arr[j] < char) {
                    j++
                }
                if (j == arr.size) {
                    break@loop
                }
                if (arr[j] == char) {
                    found++
                    j++
                }
                indexes[i] = j
            }
            if (found == a.size) {
                result.add(char.toString())
            }
            indexes[0]++
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
