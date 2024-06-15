 #include <algorithm> // for std::min
#include <unordered_map>
 #include <vector>

using std::vector;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        // value to index
        std::unordered_map<int, int> map;

        for (int i = 0; i < nums.size(); i++) {
            int x = target - nums[i];
            auto it = map.find(x);
            if (it != map.end()) {
                return std::vector<int>({it->second, i});
            }
            map[nums[i]] = i;
        }

        return std::vector<int>({0, 0});
    }
};

// test

#include <iostream>

int main() {
}