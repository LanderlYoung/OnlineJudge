#include <cstdlib>
#include <string>

using std::string;

class Solution {
public:
  int romanToInt(const std::string& s) {
    int result = 0;

    size_t i = 0;
    while (i < s.size()) {
      char c = s[i];
      int value = map(c);

      if (i + 1 < s.size() && value < map(s[i + 1])) {
        result += map(s[i + 1]) - value;
        i += 2;
      } else {
        result += value;
        i++;
      }
    }

    return result;
  }

  /**
   * Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000
   */
  int map(char c) {
    switch (c) {
    case 'I':
      return 1;
    case 'V':
      return 5;
    case 'X':
      return 10;
    case 'L':
      return 50;
    case 'C':
      return 100;
    case 'D':
      return 500;
    case 'M':
      return 1000;
    default:
      std::abort();
    }
  }
};

// test

#include <iostream>

void test(const std::string &input, int expected) {
  std::cout << input << " ->" << Solution().romanToInt(input)
            << " == " << expected << std::endl;
}

int main() {
  test("MCMXCIV", 1994);
  test("LVIII", 58);
  test("III", 3);
}