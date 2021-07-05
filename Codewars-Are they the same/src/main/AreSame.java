package main;

public class AreSame {
    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        main:
        for (int i = 0; i < a.length; i++) {
            int aNum = (int) Math.pow(a[i], 2);
            for (int j = 0; j < b.length; j++) {
                int bNum = b[j];
                if (bNum == aNum) {
                    a[i] = -1;
                    b[j] = -1;
                    continue main;
                }
            }
            return false;
        }
        return true;
    }
}
