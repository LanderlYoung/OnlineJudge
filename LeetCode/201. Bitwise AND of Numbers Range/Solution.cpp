public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int d = -1;
        while ((m & d) != (n & d)) {
            d <<= 1;
        }
        return m & d;
    }

}