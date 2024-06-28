struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode() : val(0), left(nullptr), right(nullptr) {}
  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
  TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

#include <algorithm>  // for std::min
#include <limits>

class Solution {
  int max = std::numeric_limits<int>::min();

 public:
  int maxPathSum(TreeNode *root) {
    f(root);
    return max;
  }

 private:
  int f(TreeNode *node) {
    if (!node) return 0;
    auto left = std::max(f(node->left), 0);
    auto right = std::max(f(node->right), 0);
    max = std::max(max, left + node->val + right);
    return std::max(left + node->val, right + node->val);
  }
};

// test

#include <iostream>

void test(const std::vector<int> &height, int expected) {
  int ret = Solution().trap(height);
  std::cout << ret << " == " << expected << std::endl;
}

int main() {
  test(std::vector<int>{4, 2, 0, 3, 2, 5}, 9);
  test(std::vector<int>{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
  test(std::vector<int>{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
}
