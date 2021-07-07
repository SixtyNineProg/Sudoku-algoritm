package main;

import java.util.Arrays;

public class Prime {
    public static boolean isPrime2(int num) {
        try {
            int arrSize = num + 1;
            boolean[] isPrime = new boolean[arrSize];
            Arrays.fill(isPrime, true);
            isPrime[1] = false;
            for (int i = 2; i * i < arrSize; i++)
                if (isPrime[i])
                    for (int j = i * i; j < arrSize; j += i)
                        isPrime[j] = false;

            return isPrime[num];
        } catch (IndexOutOfBoundsException | NegativeArraySizeException e) {
            return false;
        }

    }

    public static boolean isPrime(int num) {
        if (num < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static int zeros(int n) {
        System.out.println(n);
        int zeros = 0;
        while (n > 0) {
            zeros += n / 5;
            n /= 5;
        }
        return zeros;
    }
}
