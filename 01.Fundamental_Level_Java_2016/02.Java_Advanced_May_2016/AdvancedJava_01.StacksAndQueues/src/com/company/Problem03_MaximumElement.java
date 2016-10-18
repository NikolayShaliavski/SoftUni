package com.company;

import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class Problem03_MaximumElement {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int lines = Integer.parseInt(scan.nextLine());
        Stack<Integer> stack = new Stack<>();
        //Stack<Integer> maxValues = new Stack<>();
        TreeSet<Integer> maxValues = new TreeSet<>();
        //int max = 0;

        for (int i = 0; i < lines; i++) {
            String[] line = scan.nextLine().split("\\s+");
            int command = Integer.parseInt(line[0]);

            if (command == 1) {
                int num = Integer.parseInt(line[1]);
                stack.push(num);
                maxValues.add(num);
//                if (num > max) {
//                    max = num;
//                    maxValues.push(num);
//                }
            } else if (command == 2) {
                //int current = stack.pop();
//                if (current == max) {
//                    //maxValues.pop();
//                    if (maxValues.size() == 0) {
//                        max = 0;
//                    } else {
//                        max = maxValues.peek();
//                    }
//                }
                maxValues.remove(stack.pop());
            } else {
                System.out.println(maxValues.last());
            }
        }
    }
}
