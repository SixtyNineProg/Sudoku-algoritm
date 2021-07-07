package main;

public class ASum {
    public static long findNb(long m) {
        int n = 1;
        while (m > 0) {
            m -= (long) Math.pow(n, 3);
            n++;
        }
        if (m == 0) return n - 1;
        return -1;
    }
}
