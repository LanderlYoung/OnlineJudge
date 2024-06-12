package problem1122

class Solution {
  fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
    val result = IntArray(arr1.size)
    var tail = 0

    arr2.forEach { int ->
      repeat(arr1.size) { index ->
        if (arr1[index] == int) {
          arr1[index] = -1
          result[tail++] = int
        }
      }
    }

    arr1.sorted().forEach { int ->
      if (int != -1) {
        result[tail++] = int
      }
    }

    return result
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