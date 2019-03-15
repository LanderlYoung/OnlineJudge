import java.util.*

class Solution {
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val result = LinkedList<String>()
        val root = buildTree(words)

        for (y in 0 until board.size) {
            for (x in 0 until board[0].size) {
                dfs(result, board, root, x, y)
            }
        }

        return result
    }

    private fun dfs(
            result: MutableList<String>, board: Array<CharArray>,
            parent: TrieNode, x: Int, y: Int
    ) {
        if (y < 0 || y >= board.size || x < 0 || x >= board[0].size) {
            return
        }

        val char = board[y][x]
        if (char == '-') return // this cell is already stepped
        val next = parent.next[char - 'a'] ?: return

        next.word?.let {
            result.add(it)
            // use count if the problem allow multiple result
            next.word = null
        }

        board[y][x] = '-'
        dfs(result, board, next, x, y - 1)
        dfs(result, board, next, x, y + 1)
        dfs(result, board, next, x - 1, y)
        dfs(result, board, next, x + 1, y)
        board[y][x] = char
    }

    private fun buildTree(words: Array<String>): TrieNode {
        val root = TrieNode('-')

        words.forEach { word ->
            var node = root
            word.forEach {
                node = node.next[it - 'a'] ?: TrieNode(it).apply {
                    node.next[it - 'a'] = this
                }
            }
            node.word = word
        }
        return root
    }

    private data class TrieNode(
            val char: Char,
            var next: Array<TrieNode?> = Array(26) { null },
            var word: String? = null
    )
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
println(
        Solution().findWords(
                arrayOf("a".toCharArray(),
                        "a".toCharArray()),
                arrayOf("aaa")
        )
)
