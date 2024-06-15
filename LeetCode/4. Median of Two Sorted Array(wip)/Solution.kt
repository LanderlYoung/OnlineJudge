package problem4

class Solution {
  fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val array = arrayOf(nums1, nums2)

    var leftArray = 0
    var leftIndex = 0

    var rightArray = 1
    var rightIndex = 0

    if (nums1.isEmpty()) {
      leftArray = 1

      rightArray = 1
      rightIndex = nums2.size - 0
    } else if (nums2.isEmpty()) {
      rightIndex = nums1.size - 1
    } else {
      if (nums1[0] < nums2[0]) {
        leftArray = 0
      } else {
        leftArray = 1
      }

      if (nums1.last() < nums2.last()) {
        rightArray = 1
        rightIndex = nums2.size - 1
      } else {
        rightIndex = nums1.size - 1
      }
    }

    while (
      (leftArray == rightArray && leftIndex < rightIndex)
      || !(leftArray != rightArray && leftIndex == array[leftArray].size - 1 && rightIndex == 0)
    ) {

      val left = array[leftArray][leftIndex]
      val right = array[rightArray][rightIndex]

      if (left < right) {
        val leftGap = array[leftArray].size - leftIndex - 1
        val rightGap = rightIndex - 1

        val half = (leftGap + rightGap) / 2

        // falls in left array
        if (half < leftGap) {

        }

      }

    }

    return 0.0
  }
}

// test

fun test(
  // input
  s: String,
  // expected
  expected: Int,
) {

  // println("${Solution().romanToInt(s)} == ${expected}")
}

fun main() {
  test("III", 3)
  test("LVIII", 58)
  test("MCMXCIV", 1994)
}