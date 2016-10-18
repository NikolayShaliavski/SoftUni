package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Crossfire {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] dimentions = scan.nextLine().split(" ");
        int rows = Integer.parseInt(dimentions[0]);
        int cols = Integer.parseInt(dimentions[1]);

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>(rows);
        int value = 1;
        for (int i = 0; i < rows; i++) {
            matrix.add(new ArrayList<>(cols));
            for (int j = 0; j < cols; j++) {
                matrix.get(i).add(value);
                value++;
            }
        }
        String line = scan.nextLine();

        while (!line.equals("Nuke it from orbit")) {
            String[] command = line.split(" ");

            int row = Integer.parseInt(command[0]);
            int col = Integer.parseInt(command[1]);
            int radius = Integer.parseInt(command[2]);
            if (matrix.size() - 1 >= row && row >= 0) {
                if (matrix.get(row).size() - 1 >= col && col >= 0) {
                    matrix.get(row).set(col, 0);
                }
            }
            for (int i = 1; i <= radius; i++) {
                if (row - i >= 0 && row - i <= matrix.size() - 1) {
                    if (matrix.get(row - i).size() - 1 >= col && col >= 0) {
                        matrix.get(row - i).set(col, 0);
                    }
                }
                if (row + i <= matrix.size() - 1 && row + i >= 0) {
                    if (matrix.get(row + i).size() - 1 >= col && col >= 0) {
                        matrix.get(row + i).set(col, 0);
                    }
                }
            }
            if (matrix.size() - 1 >= row && row >= 0) {
                int left = col - 1;
                int right = col + 1;
                for (int i = 1; i <= radius; i++) {
                    if (left >= 0 && left <= matrix.get(row).size() - 1) {
                        matrix.get(row).set(left, 0);
                    }
                    if (right >= 0 && right <= matrix.get(row).size() - 1) {
                        matrix.get(row).set(right, 0);
                    }
                    left--;
                    right++;
                }
            }
            for (int i = 0; i < matrix.size(); i++) {
                matrix.get(i).removeAll(Arrays.asList(0));
                if (matrix.get(i).size() <= 0) {
                    matrix.remove(i);
                }
            }
            line = scan.nextLine();
        }
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (j < matrix.get(i).size() - 1) {
                    System.out.printf("%d ", matrix.get(i).get(j));
                } else {
                    System.out.printf("%d", matrix.get(i).get(j));
                }
            }
            System.out.println();
        }
    }
}
