package problem70

class Solution {
  lateinit var store: IntArray

  fun climbStairs(n: Int): Int {
    store = IntArray(n)
    return inner(n)
  }

  fun inner(n: Int): Int {
    if (n == 2) {
      return 2
    } else if (n == 1) {
      return 1
    }

    if (store[n - 1] != 0) {
      return store[n - 1]
    }

    val x = inner(n - 2) + inner(n - 1)
    store[n - 1] = x
    return x
  }
}
