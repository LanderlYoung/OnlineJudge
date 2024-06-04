#include <algorithm>

class Solution {
public:
    int trap(vector<int>& height) {
        if (height.empty()) return 0;

        int total = 0;

        int leftMax = 0;
        int rightMax = 0;

        int left = 0;
        int right = height.size() - 1;

        while (left < right) {
            leftMax = std::max(leftMax, height[left]);
            rightMax = std::max(rightMax, height[right]);

            if (leftMax < rightMax) {
                // move left forward
                total += leftMax - height[left];
                left++;
            } else {
                // move right forward
                total += rightMax - height[right];
                right--;
            }
        }

        return total;
    }
};

