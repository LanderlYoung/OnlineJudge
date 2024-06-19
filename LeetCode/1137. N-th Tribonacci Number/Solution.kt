package problem1137

class Solution {
  companion object {
    val store = IntArray(38) { -1 }.also {
      it[0] = 0
      it[1] = 1
      it[2] = 1

    }
  }

  fun tribonacci(n: Int): Int = if (store[n] != -1) {
    store[n]
  } else {
    val ret = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3)
    store[n] = ret
    ret
  }
}
