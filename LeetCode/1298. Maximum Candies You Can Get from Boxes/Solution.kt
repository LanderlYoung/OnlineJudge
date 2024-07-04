package problem1298

import matrix1d
import matrix2d
import shouldBeEqualTo
import java.util.*

class Solution {
  fun maxCandies(
    status: IntArray,
    candies: IntArray,
    keys: Array<IntArray>,
    containedBoxes: Array<IntArray>,
    initialBoxes: IntArray,
  ): Int {
    var count = 0

    val keysInHand = HashSet<Int>()
    val boxInHand = LinkedList<Int>()
    val newBoxes = ArrayList<Int>()
    initialBoxes.forEach { box -> newBoxes.add(box) }

    var hasNewKey = false
    while (newBoxes.isNotEmpty() || hasNewKey) {
      boxInHand.addAll(newBoxes)
      newBoxes.clear()
      hasNewKey = false

      val it = boxInHand.iterator()
      while (it.hasNext()) {
        val box = it.next()
        // already open || has key to open
        if (status[box] == 1 || keysInHand.contains(box)) {
          status[box] = -1 // used
          it.remove()

          count += candies[box]
          hasNewKey = hasNewKey || keys[box].isNotEmpty()

          keys[box].forEach { keysInHand.add(it) }
          containedBoxes[box].forEach {
            // not used
            if (status[it] != -1) {
              newBoxes.add(it)
            }
          }
        }
      }
    }

    return count
  }
}

fun main() {
  fun test(
    status: String, candies: String, keys: String, containedBoxes: String, initialBoxes: String,
    expected: Int,
  ) {
    val result = Solution().maxCandies(
      matrix1d(status), matrix1d(candies),
      matrix2d(keys), matrix2d(containedBoxes), matrix1d(initialBoxes)
    )
    println("$ -> ${result} == ${expected} -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(
    status = "[1,0,1,0]",
    candies = "[7,5,4,100]",
    keys = "[[],[],[1],[]]",
    containedBoxes = "[[1,2],[3],[],[]]",
    initialBoxes = "[0]",
    expected = 16
  )

  test(
    status = "[1,0,0,0,0,0]",
    candies = "[1,1,1,1,1,1]",
    keys = "[[1,2,3,4,5],[],[],[],[],[]]",
    containedBoxes = "[[1,2,3,4,5],[],[],[],[],[]]",
    initialBoxes = "[0]",
    expected = 6
  )
}