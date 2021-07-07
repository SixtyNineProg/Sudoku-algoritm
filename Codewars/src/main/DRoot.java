package main;

public class DRoot {
    public static int digital_root(int n) {
        while (true) {
            if (Integer.toString(n).length() == 1) break;
            else n = Integer.toString(n).chars().map(Character::getNumericValue).sum();
        }
        return n;
    }
}
