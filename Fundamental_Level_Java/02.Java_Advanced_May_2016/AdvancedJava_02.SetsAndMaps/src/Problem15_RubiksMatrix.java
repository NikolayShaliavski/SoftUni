import java.util.Scanner;

public class Problem15_RubiksMatrix {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] dimentions = scan.nextLine().split(" ");
        int rows = Integer.parseInt(dimentions[0]);
        int cols = Integer.parseInt(dimentions[1]);

        int[][] matrix = fillMatrix(rows, cols);
        int commands = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < commands; i++) {
            String[] currentCommand = scan.nextLine().split(" ");
            String direction = currentCommand[1];
            int rowOrCol = Integer.parseInt(currentCommand[0]);
            int steps = Integer.parseInt(currentCommand[2]);

            shuffleMatrix(matrix, direction, rowOrCol, steps);
        }
        rearrangeMatrix(matrix);
    }

    private static void rearrangeMatrix(int[][] matrix) {
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

    private static void shuffleMatrix(int[][] matrix, String direction, int rowOrCol, int steps) {

        if ("up".equals(direction) || "down".equals(direction)) {
            int col = rowOrCol;
            int[] temp = new int[matrix.length];
            steps %= matrix.length;

            if ("up".equals(direction)) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 0; j < temp.length - 1; j++) {
                        temp[j] = matrix[j + 1][col];
                    }
                    temp[temp.length - 1] = matrix[0][col];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[j][col] = temp[j];
                    }
                }
            } else if ("down".equals(direction)) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 1; j < temp.length; j++) {
                        temp[j] = matrix[j - 1][col];
                    }
                    temp[0] = matrix[matrix.length - 1][col];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[j][col] = temp[j];
                    }
                }
            }
        } else if ("right".equals(direction) || "left".equals(direction)) {
            int row = rowOrCol;
            int[] temp = new int[matrix[row].length];
            steps %= matrix[row].length;

            if ("left".equals(direction)) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 0; j < temp.length - 1; j++) {
                        temp[j] = matrix[row][j + 1];
                    }
                    temp[temp.length - 1] = matrix[row][0];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[row][j] = temp[j];
                    }
                }
            } else if ("right".equals(direction)) {
                for (int i = 0; i < steps; i++) {
                    for (int j = 1; j < temp.length; j++) {
                        temp[j] = matrix[row][j - 1];
                    }
                    temp[0] = matrix[row][matrix[row].length - 1];
                    for (int j = 0; j < temp.length; j++) {
                        matrix[row][j] = temp[j];
                    }
                }
            }
        }
    }

    private static int[][] fillMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = counter;
                counter++;
            }
        }
        return matrix;
    }
}
