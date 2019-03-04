public class Solution {
    public double myPow(double x,  int n) {
        switch (n) {
            case 1: return x;
            case 0: return 1;
            case -1: return 1/x;
        }
        double r = myPow(x, n/2);
        r *= r;
        boolean positive = n > 0;
        if ((n % 2) != 0) r *= positive ? x : 1 / x;
        return r;
    }
}