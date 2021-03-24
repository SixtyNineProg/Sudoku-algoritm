public class Main {
    public static void main(String[] args) {
        subtraction(5);
        recur(99);
    }

    private static void subtraction(int num) {
        System.out.println(num);
        if (num > 1) {
            subtraction(--num);
            System.out.println("hello");
        }
    }

    private static void recur(int i){
        if (i <= 100){
            System.out.println(i);
            recur(++i);
            System.out.println(i);
        }
    }
}
