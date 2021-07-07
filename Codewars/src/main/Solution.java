package main;

import java.lang.StringBuilder;
public class Solution {

    public static String toCamelCase(String s) {
        boolean upper = false;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (!((a >= 65 && a <= 90) || (a >= 97 && a <= 121))) {
                upper = true;
            } else {
                if (upper) {
                    a = a >= 90 ? (char) (a - 32) : a;
                    upper = false;
                }
                output.append(a);
            }
        }
        return output.toString();
    }
}
