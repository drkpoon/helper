import java.math.BigInteger;

public class Fibonacci {
    static BigInteger[] getFibonacciList(int lower, int upper) {
        BigInteger[] result = new BigInteger[upper - lower + 1];
        BigInteger x1 = BigInteger.valueOf(-1);
        BigInteger x2 = BigInteger.valueOf(1);
        BigInteger sum = BigInteger.valueOf(1);
        int count = 0;
        for (int i = 0; i <= upper; i++) {
            sum = x1.add(x2);
            x1 = x2;
            x2 = sum;
            if (i >= lower) {
                result[count] = sum;
                count++;
            }
        }
        return result;
    }

    static BigInteger getFibonacciNumber(int n) {
        BigInteger result = BigInteger.valueOf(0);
        BigInteger x1 = BigInteger.valueOf(-1);
        BigInteger x2 = BigInteger.valueOf(1);
        for (int i = 0; i <= n; i++) {
            result = x1.add(x2);
            x1 = x2;
            x2 = result;
        }
        return result;
    }
}
