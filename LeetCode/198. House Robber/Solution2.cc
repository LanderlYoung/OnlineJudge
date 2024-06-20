#include <algorithm>  // for std::min
#include <cstddef>
#include <vector>

using std::vector;

class Solution {
 public:
  int rob(const vector<int>& nums) {
    /// step 1: define sub problem
    // let's say f(i) means how much you can rob in nums[0..<i] (exclusive)
    // so the answer should be f(nums.size - 1)

    /// step 2: determine recursion formula
    // for f(i), we have two solutions
    //    -- 1. don't take f(i), so it's f(i - 1)
    //    -- 2. take f(i), so it's f(i - 2) + nums[i - 1]
    // either way, we take the bigger one
    // so the formula is: 
    //    f(i) = max(f(i - 1), f(i - 2) + nums[i - 1])

    /// step 2.1: be careful about edge condition
    //  - f(0) = 0
    //  - f(o) = num[0]

    /// step 3: choose calculate direction
    //  1. bottom up
    //     start from basic sub problem, enlarge the scope
    //  2. top down
    //     assume the sub problems are solved, directly calculate the final (large scope problem)
    //     so, usually it's a recursive pattern, to speed up (avoid unnecessary duplicate calls),
    //     a memo data structure is need.

    /// step 4: optimize space usage
    //    from the recursion formula: 
    //        f(i) = max(f(i - 1), f(i - 2) + nums[i - 1])
    //
    //    we noticed that only TWO previously results are used, the more previously items
    //    are not really necessary.
    //    So we change from using an array to using two values
    //       prev2 -> prev1 -> [i]
    //
    //    int prev1, prev2;
    //    for (i in 2..nums.size) {
    //      money = max(prev1, prev2 + nums[i - 1]);
    //      prev2 = prev1;
    //      prev1 = money;
    //    }
    //    return prev1;
    //
    //    see Solution2.kt

    int prev2 = 0;
    int prev1 = nums[0];

    // prev2 -> prev1 -> [i]
    for (size_t i = 1; i < nums.size(); i++) {
      int money = std::max(prev1, prev2 + nums[i]);
      prev2 = prev1;
      prev1 = money;
    }

    return prev1;
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
