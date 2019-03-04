/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* head1, ListNode* head2) {
        if (head1 == nullptr && head2 == nullptr) return nullptr;
        
        auto head = ListNode(0);
        
        auto carry = 0;
        auto current = &head;
        while (head1 != nullptr || head2 != nullptr || carry != 0) {
            auto number = carry;
            if (head1 != nullptr) number += head1->val;
            if (head2 != nullptr) number += head2->val;
            auto node = new ListNode(number);
            if (number >= 10) {
                carry = 1;
                node->val = number - 10;
            } else {
                carry = 0;
            }
            head1 = head1 != nullptr ? head1->next : nullptr;
            head2 = head2 != nullptr ? head2->next : nullptr;
            current->next = node;
            current = node;
        }

        return head.next;
    }
};
