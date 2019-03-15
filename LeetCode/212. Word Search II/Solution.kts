import kotlin.math.abs

class Solution {
    private data class Point(val x: Int, val y: Int)

    private class PS(val point: Point, var used: Boolean = false)

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val result = mutableSetOf<String>()
        val map = createMap(board)

        words.forEachIndexed { index, word ->
            if (index != 0) {
                clearMap(map)
            }

            if (findWord(map, word)) {
                result.add(word)
            }
        }
        return result.toList()
    }

    private fun isAdjacent(p0: Point?, p1: Point): Boolean {
        return p0 == null || abs(p0.y - p1.y) + abs(p0.x - p1.x) == 1
    }

    private fun findWord(
        map: Map<Char, MutableList<PS>>,
        word: String,
        fromIndex: Int = 0,
        currentPoint: Point? = null
    ): Boolean {
        if (fromIndex == word.length) return true

        val list = map[word[fromIndex]] ?: emptyList<PS>()
        for (ps in list) {
            if (!ps.used && isAdjacent(currentPoint, ps.point)) {
                ps.used = true
                if (findWord(map, word, fromIndex + 1, ps.point)) {
                    return true
                }
                ps.used = false
            }
        }

        return false
    }

    private fun createMap(board: Array<CharArray>): Map<Char, MutableList<PS>> =
        HashMap<Char, MutableList<PS>>().apply {
            board.forEachIndexed { y, chars ->
                chars.forEachIndexed { x, char ->
                    getOrPut(char) { mutableListOf() }.add(PS(Point(x, y)))
                }
            }
        }

    private fun clearMap(map: Map<Char, MutableList<PS>>) {
        map.values.forEach { list ->
            list.forEach {
                it.used = false
            }
        }
    }
}

println(
    Solution().findWords(
        arrayOf(
            "oaan".toCharArray(),
            "etae".toCharArray(),
            "ihkr".toCharArray(),
            "iflv".toCharArray()
        ),
        arrayOf("oath", "pea", "eat", "rain")
    )
)
println(
    Solution().findWords(
        arrayOf("a".toCharArray()),
        arrayOf("a", "a")
    )
)
