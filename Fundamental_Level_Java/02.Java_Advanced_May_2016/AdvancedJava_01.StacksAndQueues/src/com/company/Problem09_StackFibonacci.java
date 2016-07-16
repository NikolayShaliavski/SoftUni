package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Problem09_StackFibonacci {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int fibNumber = Integer.parseInt(scan.nextLine());

        Stack<Long> fibonacciStack = new Stack<>();
        fibonacciStack.push(1L);
        fibonacciStack.push(1L);

        for (int i = 0; i < fibNumber - 1; i++) {
            long second = fibonacciStack.pop();
            long first = fibonacciStack.pop();
            long currentFib = first + second;
            fibonacciStack.push(second);
            fibonacciStack.push(currentFib);
        }
        System.out.println(fibonacciStack.pop());
    }
}
