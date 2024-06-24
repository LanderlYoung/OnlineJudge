package problem79

import shouldBeEqualTo

class Solution {
  fun exist(board: Array<CharArray>, word: String): Boolean {
    fun dfs(x: Int, y: Int, i: Int): Boolean {
      // search end
      if (i == word.length) {
        return true
      }

      // invalid index
      if (x !in board.indices || y !in board[0].indices) {
        return false
      }

      if (
        board[x][y] == '!' ||
        board[x][y] != word[i]
      ) {
        return false
      }

      val char = board[x][y]
      board[x][y] = '!'

      val match =
        dfs(x - 1, y, i + 1) ||
            dfs(x + 1, y, i + 1) ||
            dfs(x, y - 1, i + 1) ||
            dfs(x, y + 1, i + 1)

      // restore
      board[x][y] = char
      return match
    }

    for (x in board.indices) {
      for (y in board[0].indices) {
        if (dfs(x, y, 0)) {
          return true
        }
      }
    }

    return false
  }
}

fun main() {
  fun test(
    board: List<String>,
    word: String,
    expected: Boolean,
  ) {
    val result = Solution().exist(board.map { it.toCharArray() }.toTypedArray(), word)
    println("${board} -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(
    listOf(
      "ABCE",
      "SFCS",
      "ADEE"
    ),
    "ABCCED",
    true
  )

  test(
    listOf(
      "ABCE",
      "SFCS",
      "ADEE",
    ),
    "SEE",
    true
  )

  test(
    listOf(
      "ABCE",
      "SFCS",
      "ADEE"
    ),
    "ABCB",
    false
  )
}