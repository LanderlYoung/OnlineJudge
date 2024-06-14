package problem23

import java.util.PriorityQueue

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
  fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val queue = PriorityQueue<ListNode> { lhs, rhs ->
      lhs.`val` - rhs.`val`
    }

    repeat(lists.size) {
      lists[it]?.let { queue.offer(it) }
    }

    val head = ListNode(0)
    var current = head

    while (queue.isNotEmpty()) {
      val node = queue.poll()

      current.next = node
      current = node

      node.next?.let { queue.offer(it) }
    }

    return head.next
  }
}

class ListNode(var `val`: Int) {
  var next: ListNode? = null
}

// test

fun main() {
  // Solution().mergeKLists(arrayOf(
  //   intArrayOf(1, 4, 5),
  //   intArrayOf(1, 3, 4),
  //   intArrayOf(2, 6)
  // ))
  // Input: lists = [[1,4,5],[1,3,4],[2,6]] Output: [1,1,2,3,4,4,5,6]
  Solution().mergeKLists(emptyArray())
}