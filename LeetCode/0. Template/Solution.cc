 #include <algorithm> // for std::min
 #include <vector>

using std::vector;

class Solution {
public:
    int trap(const vector<int>& height) {
        int result = 0;

        return result;
    }
};

// test

#include <iostream>

void test(const std::vector<int>& height, int expected) {
  int ret = Solution().trap(height);
  std::cout << ret << " == " << expected << std::endl;
}

int main() {
    test(std::vector<int>{4, 2, 0, 3, 2, 5}, 9);
    test(std::vector<int>{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
    test(std::vector<int>{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
}