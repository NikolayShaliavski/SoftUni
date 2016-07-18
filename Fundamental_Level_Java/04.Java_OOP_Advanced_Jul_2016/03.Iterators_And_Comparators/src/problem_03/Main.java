package problem_03;

import problem_03.collection.CustomStack;
import problem_03.contracts.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> myStack = new CustomStack<>(Integer.class);

        String line = reader.readLine();
        while (!line.equals("END")) {
            String[] params = line.split("[\\s,]+");
            executeCommand(params, myStack);
            line = reader.readLine();
        }
        for (int i = 0; i < 2; i++) {
            for (Integer integer : myStack) {
                System.out.println(integer);
            }
        }
    }

    private static void executeCommand(String[] params, Stack<Integer> myStack) {

        switch (params[0]) {
            case "Push":
                Integer[] numbersToPush = parseNumbers(params);
                myStack.push(numbersToPush);
                break;
            case "Pop":
                try {
                    myStack.pop();
                } catch (UnsupportedOperationException uoex) {
                    System.out.println(uoex.getMessage());
                }
                break;
        }
    }

    private static Integer[] parseNumbers(String[] params) {
        Integer[] numbersToPush = new Integer[params.length - 1];
        for (int i = 0; i < numbersToPush.length; i++) {
            numbersToPush[i] = Integer.valueOf(params[i + 1]);
        }
        return numbersToPush;
    }
}
