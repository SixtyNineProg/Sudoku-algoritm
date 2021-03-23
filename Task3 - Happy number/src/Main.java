public class Main {

    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            boolean result = isHappy(i);
            if (result) System.out.println(i);
        }
    }

    private static boolean isHappy(int num) {
        while (true)
            if (num >= 0 && num <= 9) break;
            else num = Integer.toString(num).chars().map(c -> (int) Math.pow(Character.digit(c, 10), 2)).sum();
        return num == 1;
    }
}
