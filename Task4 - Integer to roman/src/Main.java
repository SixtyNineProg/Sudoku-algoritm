import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Integer, String> tableRomanChars = new HashMap<Integer, String>() {{
        put(1, "I");
        put(2, "II");
        put(3, "III");
        put(4, "IV");
        put(5, "V");
        put(6, "VI");
        put(7, "VII");
        put(8, "VIII");
        put(9, "IX");
        put(10, "X");
        put(20, "XX");
        put(30, "XXX");
        put(40, "XL");
        put(50, "L");
        put(60, "LX");
        put(70, "LXX");
        put(80, "LXXX");
        put(90, "XC");
        put(100, "C");
        put(200, "CC");
        put(300, "CCC");
        put(400, "CD");
        put(500, "D");
        put(600, "DC");
        put(700, "DCC");
        put(800, "DCCC");
        put(900, "CM");
        put(1000, "M");
        put(2000, "MM");
        put(3000, "MMM");
    }};

    public static void main(String[] args) {
        System.out.println(convertIntToRoman(58));
    }

    private static String convertIntToRoman(int num) {
        StringBuilder resultLess = new StringBuilder();
        StringBuilder resultMore = new StringBuilder();
        for (int i = 1; num > 0; num /= 10, i *= 10) {
            int tempNum = num % 10;
            if (i <= 1000) resultLess.insert(0, tableRomanChars.get(tempNum*i));
            else resultMore.insert(0, tableRomanChars.get(tempNum*i/10000));
        }
        if (!resultMore.toString().equals("")) {
            resultMore.insert(0, "(");
            resultMore.append(")");
        }
        return resultMore.toString() + resultLess.toString();
    }
}
