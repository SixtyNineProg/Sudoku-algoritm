public class Main {
    public static void main(String[] args) {
        int numDays = 5;
        for (int i = 0; i < 1000; i++) {
            double tmp = calculateStudents(numDays, i);
            if (tmp % 1 == 0) {
                System.out.println(tmp + "_" + i);
                break;
            }
        }
    }

    private static double calculateStudents(int numDays, int numberStudents) {
        double num1 = (double) 1 / 3;
        double result = numberStudents;
        for (int i = 1; i <= numDays; i++) {
            result -= result * num1 + num1;
        }
        return result;
    }
}
