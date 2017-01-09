package fibonacci;

/**
 * Bottom-up calculation Nth fibonacci number
 */
public class FibonacciIterative {

    public static void main(String[] args) {
        int n = 36;

        long result = findFibNumber(n);

        System.out.println(result);
    }

    private static long findFibNumber(int n) {
        long[] fib = new long[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}
