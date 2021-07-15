package main;

public class Solution {

    public static String toCamelCase(String str) {
        System.out.println(str);
        boolean upper = false;
        boolean first = true;
        boolean space = false;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if (a == 32) {
                space = true;
                continue;
            }
            if (!((a >= 65 && a <= 90) || (a >= 97 && a <= 122))) upper = true;

            if (first) {
                space = false;
                first = false;
                if (!upper) a -= 32;
            } else if (space) {
                space = false;
                if (!upper) a -= 32;
            }
            output.append(a);
        }
        return output.toString();
    }

    public static String camelCase(String str) {
        // your code here
        String[] strings = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            if (string.length() > 0) {
                stringBuilder.append(string.replaceFirst(string.substring(0, 1), string.substring(0, 1).toUpperCase()));
            }

        }
        return new String(stringBuilder);

    }
}
