package problem42

class Solution {
  fun trap(height: IntArray): Int {
    val wallLength = height.size
    var total = 0

    // + means wall is higher, -/0 means wall is lower
    val lineStatus = BooleanArray(wallLength)

    var lastCalculateLine = -1
    var lastTrappedInCalculatedLine = 0

    var line = 0
    while (true) {
      // fill lineStatus
      fillLineStatus(height, line, lineStatus)

      val trappedInLine = trappedInLine(wallLength, lineStatus)

      // add skipped lines
      if (lastCalculateLine != -1) {
        total += (line - lastCalculateLine - 1) * lastTrappedInCalculatedLine
      }
      // add current line
      total += trappedInLine

      lastCalculateLine = line
      lastTrappedInCalculatedLine = trappedInLine

      val nextLine = nextLine(height, line)
      if (nextLine < 0) {
        break
      }
      line = nextLine
    }

    return total
  }

  private fun fillLineStatus(
    height: IntArray,
    line: Int,
    lineStatus: BooleanArray,
  ) {
    repeat(height.size) { index ->
      lineStatus[index] = height[index] - line > 0
    }
  }

  private fun trappedInLine(wallLength: Int, lineStatus: BooleanArray): Int {
    var trappedInLine = 0
    var lastHigher = -1
    repeat(wallLength) { index ->
      val isHigher = lineStatus[index]
      if (isHigher) {
        if (lastHigher >= 0) {
          trappedInLine += index - lastHigher - 1
        }
        lastHigher = index
      }
    }
    return trappedInLine
  }

  private fun nextLine(height: IntArray, line: Int): Int {
    var nextLine = -1
    height.forEach { h ->
      if (h > line) {
        if (nextLine == -1 || h < nextLine) {
          nextLine = h
        }
      }
    }
    return nextLine
  }
}

run {
  val arr = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
  val ret = Solution().trap(arr)
  println("$ret == 6")
}

run {
  val arr = intArrayOf(4, 2, 0, 3, 2, 5)
  val ret = Solution().trap(arr)
  println("$ret == 9")
}