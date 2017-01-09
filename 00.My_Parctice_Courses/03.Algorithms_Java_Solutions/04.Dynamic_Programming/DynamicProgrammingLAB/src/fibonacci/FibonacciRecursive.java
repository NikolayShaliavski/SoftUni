package fibonacci;

/**
 * Calculate Nth fibonacci number
 * Top-down calculation with memoization to avoid
 * calculating already generated results
 */
public class FibonacciRecursive {

    /**
     * Array with n + 1 length to save results of calculating
     * of every fibonacci number
     */
    private static long[] fibResults;

    public static void main(String[] args) {

        int n = 4;
        fibResults = new long[n + 1];
        fibResults[1] = 1;

        long fibNth = fibonacci(n);
        System.out.println(fibNth);
    }

    private static long fibonacci(int n) {
        if (fibResults[n] != 0) {
            return fibResults[n];
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (fibResults[n] == 0) {
            fibResults[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
        return fibResults[n];
    }
}
