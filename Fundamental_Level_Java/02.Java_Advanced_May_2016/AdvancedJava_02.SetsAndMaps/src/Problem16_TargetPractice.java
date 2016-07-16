import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Problem16_TargetPractice {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] dimentions = scan.nextLine().split(" ");
        int rows = Integer.parseInt(dimentions[0]);
        int cols = Integer.parseInt(dimentions[1]);
        String snake = scan.nextLine();

        String[] bomb = scan.nextLine().split(" ");
        int row = Integer.parseInt(bomb[0]);
        int col = Integer.parseInt(bomb[1]);
        int radius = Integer.parseInt(bomb[2]);

        char[][] matrix = fillmatrix(snake, rows, cols);
        hitMatrix(matrix, row, col, radius);
        matrix = rearrangeMatrix(matrix);
        printMatrix(matrix);
    }

    private static char[][] fillmatrix(String snake, int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        boolean movement = true;
        int counter = 0;
        for (int i = rows - 1; i >= 0; i--) {
            if (movement) {
                for (int j = cols - 1; j >= 0; j--) {
                    if (counter == snake.length()) {
                        counter = 0;
                    }
                    matrix[i][j] = snake.charAt(counter);
                    counter++;
                }
            } else {
                for (int j = 0; j < cols; j++) {
                    if (counter == snake.length()) {
                        counter = 0;
                    }
                    matrix[i][j] = snake.charAt(counter);
                    counter++;
                }
            }
            movement = !movement;
        }
        return matrix;
    }

    private static void hitMatrix(char[][] matrix, int hittedRow, int hittedCol, int radius) {

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int a = hittedRow - row;
                int b = hittedCol - col;
                boolean inRadius = Math.pow(a, 2) + Math.pow(b, 2) <= Math.pow(radius, 2);
                if (inRadius) {
                    matrix[row][col] = ' ';
                }
            }
        }
    }

    private static char[][] rearrangeMatrix(char[][] matrix) {
        for (int col = 0; col < matrix[0].length; col++) {
            ArrayList<Character> column = new ArrayList<>();
            for (int row = 0; row < matrix.length; row++) {
                column.add(matrix[row][col]);
            }
            column.removeAll(Arrays.asList(' '));
            int difference = matrix.length - column.size();
            int counter = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (i < difference) {
                    matrix[i][col] = ' ';
                } else {
                    matrix[i][col] = column.get(counter);
                    counter++;
                }
            }
        }
        return matrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%s", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
