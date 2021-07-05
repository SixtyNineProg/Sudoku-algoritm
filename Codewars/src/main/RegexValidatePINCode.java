package main;

public class RegexValidatePINCode {
    public static boolean validatePin(String pin) {
        char[] mas = pin.toCharArray();
        int numDigit = mas.length;
        if (numDigit != 4 && numDigit != 6) return false;
        try {
            for (char ma : mas)
                Integer.parseInt(String.valueOf(ma));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
