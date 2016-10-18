package com.company;

import java.util.Scanner;

public class RubiksMatrix {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] dimentions = scan.nextLine().split(" ");
        int rows = Integer.parseInt(dimentions[0]);
        int cols = Integer.parseInt(dimentions[1]);

        int[][] matrix = fillMatrix(rows, cols);

        int numberOfCommands = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfCommands; i++) {
            String[] line = scan.nextLine().split(" ");
            int current = Integer.parseInt(line[0]);
            int steps = Integer.parseInt(line[2]);
            String command = line[1];
            shuffleMatrix(matrix, current, steps, command);
        }
        rearrangeMatrix(matrix);
    }

    public static int[][] fillMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int counter = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = counter;
                counter++;
            }
        }
        return matrix;
    }

    public static void shuffleMatrix(int[][] matrix, int current, int steps, String command) {
        if (command.equals("up") || command.equals("down")) {
            int col = current;
            steps %= matrix.length;
            int[] temp = new int[matrix.length];
            if (command.equals("up")) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 1; j < matrix.length; j++) {
                        temp[j - 1] = matrix[j][col];
                    }
                    temp[temp.length - 1] = matrix[0][col];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[j][col] = temp[j];
                    }
                }
            } else if (command.equals("down")) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 0; j < matrix.length - 1; j++) {
                        temp[j + 1] = matrix[j][col];
                    }
                    temp[0] = matrix[matrix.length - 1][col];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[j][col] = temp[j];
                    }
                }
            }
        } else if (command.equals("left") || command.equals("right")) {
            int row = current;
            steps %= matrix[row].length;
            int[] temp = new int[matrix[row].length];
            if (command.equals("left")) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 1; j < matrix.length; j++) {
                        temp[j - 1] = matrix[row][j];
                    }
                    temp[temp.length - 1] = matrix[row][0];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[row][j] = temp[j];
                    }
                }
            } else if (command.equals("right")) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 0; j < matrix.length - 1; j++) {
                        temp[j + 1] = matrix[row][j];
                    }
                    temp[0] = matrix[row][matrix[row].length - 1];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[row][j] = temp[j];
                    }
                }
            }
        }
    }
    public static void rearrangeMatrix (int[][] matrix) {
        int counter = 1;
        for (int a = 0; a < matrix.length; a++) {
            for (int b = 0; b < matrix[a].length; b++) {
                if (matrix[a][b] == counter) {
                    System.out.println("No swap required");
                } else {
                    int temp;
                    for (int c = a; c < matrix.length; c++) {
                        for (int d = 0; d < matrix[c].length; d++) {
                            if (matrix[c][d] == counter) {
                                temp = matrix[a][b];
                                matrix[a][b] = matrix[c][d];
                                matrix[c][d] = temp;
                                System.out.printf("Swap (%d, %d) with (%d, %d)\n", a, b, c, d);
                            }
                        }
                    }
                }
                counter++;
            }
        }
    }
}
