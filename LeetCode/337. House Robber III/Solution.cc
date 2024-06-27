#include <algorithm>  // for std::min
#include <vector>

using std::vector;
struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode() : val(0), left(nullptr), right(nullptr) {}
  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
  TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
 public:
  int rob(TreeNode *root) { return robSub(root)->val; }

  TreeNode *robSub(TreeNode *tree) {
    if (tree == nullptr) return nullptr;

    auto rob = new TreeNode{};
    rob->left = robSub(tree->left);
    rob->right = robSub(tree->right);

    int unselect = sumChild(rob);
    int select = tree->val + sumChild(rob->left) + sumChild(rob->right);

    rob->val = std::max(unselect, select);

    return rob;
  }

  int sumChild(TreeNode *tree) {
    int sum = 0;
    if (tree != nullptr && tree->left != nullptr) {
      sum += tree->left->val;
    }
    if (tree != nullptr && tree->right != nullptr) {
      sum += tree->right->val;
    }
    return sum;
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
