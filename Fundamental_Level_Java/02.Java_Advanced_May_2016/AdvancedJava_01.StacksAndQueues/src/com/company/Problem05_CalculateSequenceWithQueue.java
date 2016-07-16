package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem05_CalculateSequenceWithQueue {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        long number = Long.parseLong(scan.nextLine());

        ArrayDeque<Long> queue = new ArrayDeque<>();

        System.out.print(number + " ");

        for (int i = 0; i < 16; i++) {
            queue.add(number + 1);
            System.out.print(queue.peekLast() + " ");
            queue.add(number * 2 + 1);
            System.out.print(queue.peekLast() + " ");
            queue.add(number + 2);
            System.out.print(queue.peekLast() + " ");

            number = queue.remove();
        }
        System.out.println(number + 1);
    }
}
