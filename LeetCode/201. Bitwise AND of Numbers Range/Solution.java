public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        final int d = n - m;
        int mask = -1;

        for (int i = 0; i < 32; ++i) {
            int pos = 1 << i;
            if (d >= pos) {
                mask &= ~pos;
            }
        }
        return m & n & mask;
    }
}