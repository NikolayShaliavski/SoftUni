package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Problem10_SimpleTextEditor {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = Integer.parseInt(scan.nextLine());
        Stack<String> operations = new Stack<>();
        Stack<String> deleted = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < lines; i++) {
            String[] currentLine = scan.nextLine().split("\\s+");
            int command = Integer.parseInt(currentLine[0]);

            switch (command) {
                case 1:
                    String piece = currentLine[1];
                    appendText(builder, piece);
                    operations.push(piece);
                    break;
                case 2:
                    int delete = Integer.parseInt(currentLine[1]);
                    if (builder.length() > 0) {
                        deleteText(builder, delete, deleted);
                        operations.push(currentLine[1]);
                    } else {
                        operations.push(currentLine[1]);
                        deleted.push("");
                    }
                    break;
                case 3:
                    int index = Integer.parseInt(currentLine[1]) - 1;
                    char characterAtIndex = builder.charAt(index);
                    System.out.println(characterAtIndex);
                    break;
                case 4:
                    boolean previousCommand = isPreviousDigit(operations.peek());
                    if (previousCommand) {
                        if (!deleted.isEmpty()) {
                            String deletedCharacters = deleted.pop();
                            operations.pop();
                            appendText(builder, deletedCharacters);
                        }
                    } else {
                        String appendedText = operations.pop();
                        deleteText(builder, appendedText.length());
                    }
                    break;
            }
        }
    }

    private static void deleteText(StringBuilder builder, int delete, Stack<String> deleted) {
        int toDelete = builder.length() - delete;
        String deletedText = "";
        if (toDelete < 0) {
            deletedText = builder.toString();
        } else {
           deletedText = builder.substring(toDelete);
        }
        deleted.push(deletedText);
        builder.delete(toDelete, builder.length());
    }

    private static void deleteText(StringBuilder builder, int delete) {
        int toDelete = builder.length() - delete;
        builder.delete(toDelete, builder.length());
    }
    private static void appendText(StringBuilder builder, String piece) {
        builder.append(piece);
    }

    private static boolean isPreviousDigit(String previous) {
        try {
            int number = Integer.parseInt(previous);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
