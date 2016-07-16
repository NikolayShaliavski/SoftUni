package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Problem01_ReverseNumbersWithStack {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        String[] nums = scan.nextLine().split(" ");
        Stack<Integer> numbers = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            numbers.push(Integer.parseInt(nums[i]));
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(numbers.pop() + " ");
        }
        System.out.println();
    }
}
