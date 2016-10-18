package com.company;

import java.util.Scanner;

public class Problem07_BalancedParantheses {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        line = line.replaceAll(" ", "");

        if (line.length() % 2 != 0) {
            System.out.println("NO");
            return;
        }
        int middle = line.length() / 2;
        boolean balanced = false;

        for (int i = 0, j = line.length() - 1; i < middle; i++, j--) {
            balanced = false;
            char start = line.charAt(i);
            char end = line.charAt(j);

//            if (start == '}' || start == ']' || start == ')' || end == '{' || end == '[' || end == '(') {
//                balanced = false;
//                break;
//            }
            if (start == '}') {
                if (line.charAt(i - 1) == '{') {
                    balanced = true;
                }
            } else if (start == ']') {
                if (line.charAt(i - 1) == '[') {
                    balanced = true;
                }
            } else if (start == ')') {
                if (line.charAt(i - 1) == '[') {
                    balanced = true;
                }
            }

            if (end == '{') {
                if (line.charAt(j + 1) == '}') {
                    balanced = true;
                }
            } else if (end == '[') {
                if (line.charAt(j + 1) == ']') {
                    balanced = true;
                }
            } else if (end == '(') {
                if (line.charAt(j + 1) == ')') {
                    balanced = true;
                }
            }

            if (start == '{') {
                if (end == '}') {
                    balanced = true;
                }
            } else if (start == '[') {
                if (end == ']') {
                    balanced = true;
                }
            } else if (start == '(') {
                if (end == ')') {
                   balanced = true;
                }
            }
        }
        if (balanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
