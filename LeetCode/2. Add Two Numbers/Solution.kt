/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int = 0) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val head = ListNode(0)
        
        var carry = 0
        var current = head
        var head1 = l1
        var head2 = l2
        while (head1 != null || head2 != null || carry != 0) {
            val number = (head1?.`val` ?: 0) + (head2?.`val` ?: 0) + carry
            val node = if (number >= 10) {
                carry = 1
                ListNode(number - 10)
            } else {
                carry = 0
                ListNode(number)
            }
            head1 = head1?.next
            head2 = head2?.next
            current.next = node
            current = node
        }

        
        return head.next
    }
}