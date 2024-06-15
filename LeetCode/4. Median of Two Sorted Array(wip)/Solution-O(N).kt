package problem4_2

// O(N) solution
class Solution {
  fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val array = arrayOf(nums1, nums2)

    val leftIndex = IntArray(2)
    val leftActive = -1

    val rightIndex = IntArray(2)
    val rightActive = -1

   // initial value
   //  if (nums1.isEmpty()) {
   //    leftArray = 1
   //
   //    rightArray = 1
   //    rightIndex = nums2.size - 0
   //  } else if (nums2.isEmpty()) {
   //    rightIndex = nums1.size - 1
   //  } else {
   //    if (nums1[0] < nums2[0]) {
   //      leftArray = 0
   //    } else {
   //      leftArray = 1
   //    }
   //
   //    if (nums1.last() < nums2.last()) {
   //      rightArray = 1
   //      rightIndex = nums2.size - 1
   //    } else {
   //      rightIndex = nums1.size - 1
   //    }
   //  }
   //
   //  while (
   //    (leftArray == rightArray && leftIndex < rightIndex) // not meet
   //    || !(leftArray != rightArray && leftIndex == array[leftArray].size - 1 && rightIndex == 0)
   //  ) {
   //
   //
   //  }


    return 0.0
  }
}
