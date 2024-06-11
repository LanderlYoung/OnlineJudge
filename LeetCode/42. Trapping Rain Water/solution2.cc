 #include <algorithm> // for std::min
 #include <vector>

 using std::vector;

class Solution {
public:
    int trap(const vector<int>& height) {
        vector<int> stack(height.size(), -1);
        int sp = -1;

        int result = 0;

        for (int i = 0; i < height.size(); i++) {
            int h = height[i];

            // new wall is higher
            while(sp >= 0 && h >= height[stack[sp]]) {
                if (sp - 1 >= 0) {
                    int width = i - stack[sp - 1] - 1;
                    int trappedSegmentHeight = std::min(h, height[stack[sp - 1]]) - height[stack[sp]];

                    result += width * trappedSegmentHeight;
                }

                stack[sp] = -1; // for debug
                sp--;
                // stack.pop_back();
            }

            // sp++;
            stack[++sp] = i;
            // stack.push_back(i);
        }

        return result;
    }
};

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