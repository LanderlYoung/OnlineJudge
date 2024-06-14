#include <limits>

class Solution {
public:
  int reverse(int x) {
    int r = 0;
    while (x != 0) {
      int v = x % 10;
      if (r > std::numeric_limits<int>::max() / 10||
          r < std::numeric_limits<int>::min() / 10) {
        return 0;
      }
      r = 10 * r + v;

      x /= 10;
    }

    return r;
  }
};
