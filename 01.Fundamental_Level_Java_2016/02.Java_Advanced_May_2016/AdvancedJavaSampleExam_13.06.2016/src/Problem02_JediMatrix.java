import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem02_JediMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] dimentions = reader.readLine().split("[\\s]+");
        int rows = Integer.valueOf(dimentions[0]);
        int cols = Integer.valueOf(dimentions[1]);

        int[][] matrix = new int[rows][cols];
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = counter;
                counter++;
            }
        }

        String line = reader.readLine();
        long allStars = 0;

        while (!line.equals("Let the Force be with you")) {
            String[] ivoDimentions = line.split("[\\s]+");
            String[] evilDimentions = reader.readLine().split("[\\s]+");

            allStars += collectStars(ivoDimentions, evilDimentions, matrix);
            line = reader.readLine();
        }
        System.out.println(allStars);
    }

    private static long collectStars(String[] ivoDimentions, String[] evilDimentions, int[][] matrix) {
        long collected = 0;
        int ivoRow = Integer.valueOf(ivoDimentions[0]);
        int ivoCol = Integer.valueOf(ivoDimentions[1]);

        int evilRow = Integer.valueOf(evilDimentions[0]);
        int evilCol = Integer.valueOf(evilDimentions[1]);

        for (int row = evilRow; row >= 0; row--) {
            if (isInMatrix(row, evilCol, matrix)) {
                matrix[row][evilCol] = 0;
            }
            evilCol--;
        }

        for (int row = ivoRow; row >= 0; row--) {
            if (isInMatrix(row, ivoCol, matrix)) {
                collected += matrix[row][ivoCol];
            }
            ivoCol++;
        }
        return collected;
    }

    private static boolean isInMatrix(int row, int col, int[][] matrix) {
        boolean isInMatrix = false;
        if ((row >= 0 && row < matrix.length) && (col >= 0 && col < matrix[0].length)) {
            isInMatrix = true;
        }
        return isInMatrix;
    }
}
