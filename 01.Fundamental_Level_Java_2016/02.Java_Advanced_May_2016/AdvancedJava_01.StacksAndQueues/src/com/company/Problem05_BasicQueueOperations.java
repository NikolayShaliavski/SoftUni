package com.company;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem05_BasicQueueOperations {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] commands = scan.nextLine().split("\\s+");
        int enqueue = Integer.parseInt(commands[0]);
        int dequeue = Integer.parseInt(commands[1]);
        int toFind = Integer.parseInt(commands[2]);
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < enqueue; i++) {
            queue.add(scan.nextInt());
        }
        for (int i = 0; i < dequeue; i++) {
            queue.poll();
        }

        if (queue.contains(toFind)) {
            System.out.println(true);
        } else if (queue.size() == 0) {
            System.out.println(0);
        } else {
            System.out.println(queue.stream().sorted().collect(Collectors.toList()).get(0));
        }
    }
}
