package problem1122_2

class Solution {
  fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
    val store = IntArray(1001)
    arr1.forEach { int ->
      store[int]++
    }

    var index = 0

    arr2.forEach { int ->
      repeat(store[int]) {
        arr1[index++] = int
      }
      store[int] = 0
    }

    store.forEachIndexed { int, count ->
      repeat(count) {
        arr1[index++] = int
      }
    }
    return arr1
  }
}

// test

fun test(
  // input
  arr1: IntArray,
  arr2: IntArray,

  // expected
  expected: IntArray,
) {

  val result = Solution().relativeSortArray(arr1, arr2).toList()
  println("$result == ${expected.toList()} -> ${result == expected.toList()}")
}

fun main() {
  //
  // Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
  // Output: [22,28,8,6,17,44]
  test(
    intArrayOf(28, 6, 22, 8, 44, 17),
    intArrayOf(22, 28, 8, 6),
    intArrayOf(22, 28, 8, 6, 17, 44),
  )

  // Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
  // Output: [2,2,2,1,4,3,3,9,6,7,19]
  test(
    intArrayOf(2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19),
    intArrayOf(2, 1, 4, 3, 9, 6),
    intArrayOf(2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19)
  )

}