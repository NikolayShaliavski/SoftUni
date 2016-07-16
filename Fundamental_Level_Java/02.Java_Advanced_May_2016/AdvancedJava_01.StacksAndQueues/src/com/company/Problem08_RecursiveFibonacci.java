package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Problem08_RecursiveFibonacci {

    public static HashMap<Integer, Long> calculated = new HashMap<>();

    //optimized recursion using memoization -> in HashMap we save already calculated fibonacci numbers and if they exist in our Map we return them,
    //instead invoking again getFibonacci method ->Integer fibonacciNumber is our Key -> search if necessary in GOOGLE to understand better memoization
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int fibonacciNumber = scan.nextInt();
        scan.nextLine();

        System.out.println(getFibonacci(fibonacciNumber));
    }

    private static long getFibonacci(int fibonacciNumber) {
        if (fibonacciNumber == 1 || fibonacciNumber == 0) {
            return 1;
        }

        if (calculated.containsKey(fibonacciNumber)) {
            return calculated.get(fibonacciNumber);
        } else {
            long result = getFibonacci(fibonacciNumber - 1) + getFibonacci(fibonacciNumber - 2);
            calculated.put(fibonacciNumber, result);
            return result;
        }
    }
}
