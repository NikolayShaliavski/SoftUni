package com.company;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Problem02_BasicStackOperations {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] commands = scan.nextLine().split("\\s+");

        int elementsToPush = Integer.parseInt(commands[0]);
        int elementsToPop = Integer.parseInt(commands[1]);
        int elementsToFind = Integer.parseInt(commands[2]);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < elementsToPush; i++) {
            stack.push(scan.nextInt());
        }
        for (int i = 0; i < elementsToPop; i++) {
            stack.pop();
        }
        if (stack.contains(elementsToFind)) {
            System.out.println(true);
        } else if (stack.isEmpty()) {
            System.out.println(0);
        } else {
            List<Integer> sorted = stack.stream().sorted().collect(Collectors.toList());
            System.out.println(sorted.get(0));
        }
    }
}
