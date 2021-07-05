public class RowSumOddNumbers {
    public static int rowSumOddNumbers(int n) {
        int i = 1;
        int numSkip = 1;
        int startNum = 1;
        while (i != n) {
            numSkip += i;
            i++;
        }
        for (int j = 0; j < numSkip - 1; j++) {
            startNum += 2;
        }
        int result = startNum;
        for (int j = 0; j < n - 1; j++) {
            startNum += 2;
            result += startNum;
        }
        return result;
    }
}
