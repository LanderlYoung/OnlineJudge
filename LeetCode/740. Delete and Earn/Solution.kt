package problem740


class Solution {
  fun deleteAndEarn(nums: IntArray): Int {
    nums.sort()
    val numAndCount = ArrayList<Pair<Int, Int>>()

    var i = 0
    while (i < nums.size) {
      val num = nums[i]
      var count = 1
      while (i + 1 < nums.size && nums[i + 1] == num) {
        count++
        i++
      }
      numAndCount.add(num to count)
      i++
    }

    // 1. define f(k): Pair<earn: Int, maxExistedNum: Int>
    //    earn: means how much can earn in numAndCount[0..k]
    //    maxExistedNum: means in such condition, the max existed (non-deleted) num is what
    //    so the answer should be f(numAndCount.size - 1)
    // 2. recursion formula:
    //    f(k) =
    //      if (numsAndCount[k].first -1 == numsAndCount[k - 1) {
    //         // if numbers are adjacent
    //         max(f(k - 1), f(k - 2) + numsAndCount[i].first * numsAndCount[i].second)
    //      } else {
    //         f(k - 1) + numsAndCount[i].first * numsAndCount[i].second
    //      }
    //

    // prev2 -> prev 1
    var prev2 = 0
    var prev1 = numAndCount[0].first * numAndCount[0].second
    var prev1MaxExitedNum = numAndCount[0].first

    for (i in 1..<numAndCount.size) {
      val (num, count) = numAndCount[i]

      val earn: Int
      val maxExistedNum: Int

      if (num - 1 == prev1MaxExitedNum) {
        if (prev1 > prev2 + num * count) {
          // strategy one:
          // [x][x][i]
          //     v

          earn = prev1
          maxExistedNum = prev1MaxExitedNum
        } else {
          // strategy one:
          // [x][x][i]
          //  v     v
          earn = prev2 + num * count
          maxExistedNum = num
        }
      } else {
        earn = prev1 + num * count
        maxExistedNum = num
      }

      prev2 = prev1
      prev1 = earn
      prev1MaxExitedNum = maxExistedNum
    }

    return prev1
  }
}

// test
fun test(
  // input
  vararg input: Int,
  // expected
  expected: Int,
) {
  val result = Solution().deleteAndEarn(input.clone())
  println("${input.contentToString()} -> ${result} == ${expected} -> ${result == expected}")
}

fun main() {
  test(3, 1, expected = 4)
  test(3, 4, 2, expected = 6)
  test(2, 2, 3, 3, 3, 4, expected = 9)
}