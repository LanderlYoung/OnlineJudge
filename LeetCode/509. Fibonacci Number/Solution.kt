package probolem509

class Solution {
  // 0 <= n <= 30
  companion object {
    val store = IntArray(31) { -1 }.also {
      it[0] = 0
      it[1] = 1
    }
  }

  fun fib(n: Int): Int = if (store[n] != -1) {
    store[n]
  } else {
    val ret = fib(n - 1) + fib(n - 2)
    store[n] = ret
    ret
  }
}
