#include <algorithm> // for std::min
#include <queue>
#include <vector>

using std::vector;

struct ListNode {
  int val;
  ListNode *next;
  ListNode() : val(0), next(nullptr) {}
  ListNode(int x) : val(x), next(nullptr) {}
  ListNode(int x, ListNode *next) : val(x), next(next) {}
};

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
  ListNode *mergeKLists(vector<ListNode *> &lists) {
    auto cmp = [](const ListNode *lhs, const ListNode *rhs) { 
        return lhs->val > rhs->val;
    };
    std::priority_queue<ListNode *, std::vector<ListNode *>, decltype(cmp)> queue;
    ListNode hh;
    auto head = &hh;
    auto current = head;

    std::for_each(std::begin(lists), std::end(lists), [&](ListNode *n) {
      if (n) {
        queue.push(n);
      }
    });

    while (!queue.empty()) {
      auto node = queue.top();
      queue.pop();

      current->next = node;
      current = node;

      if (node->next) {
        queue.push(node->next);
      }
    }

    return head->next;
  }
};


int main() {}