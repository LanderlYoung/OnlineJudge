#include <algorithm>  // for std::min
#include <vector>

using std::vector;

class Solution {
 public:
  int rob(const vector<int>& nums) {
    if (nums.size() == 1) {
      return nums[0];
    }

    vector<int> money(nums.size());
    money[0] = nums[0];

    // [x][x][x][i]
    //     v     v <- solution 1
    //  v        v <- solution 2
    // see which is bigger
    for (size_t i = 1; i < nums.size(); i++) {
      int m = nums[i];
      if (i >= 2) {
        m = std::max(m, money[i - 2] + nums[i]);
      }
      if (i >= 3) {
        m = std::max(m, money[i - 3] + nums[i]);
      }
      money[i] = m;
    }

    return std::max(money[nums.size() - 1], money[nums.size() - 2]);
  }
};

// test

#include <initializer_list>
#include <iostream>

void test(std::initializer_list<int> input, int expected) {
  int ret = Solution().rob(std::vector<int>{input});
  std::cout << ret << " == " << expected << std::endl;
}

int main() {
  test({2, 1, 1, 2}, 4);
  test({1, 2, 3, 1}, 4);
  test({2, 3, 2}, 4);
  test({2, 4, 3}, 5);
  test({2, 4, 3, 2}, 6);
  test({1, 2, 3, 1}, 4);
  test({2, 7, 9, 3, 1}, 12);
}
