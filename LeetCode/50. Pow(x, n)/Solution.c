double myPow(double x,  int n) {
    switch (n) {
        case 1: return x;
        case 0: return 1;
        case -1: return 1/x;
    }
    double r = myPow(x, n/2);
    r *= r;
    if ((n % 2) != 0) r *= n > 0 ? x : 1 / x;
    return r;
}