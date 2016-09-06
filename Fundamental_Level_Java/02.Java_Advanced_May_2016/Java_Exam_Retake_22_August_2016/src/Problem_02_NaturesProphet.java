import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_02_NaturesProphet {

    private static int[] garden;
    private static boolean[] matrix;
    private static int initRows;
    private static int initCols;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] dimentions = reader.readLine().split(" ");
        initRows = Integer.valueOf(dimentions[0]);
        initCols = Integer.valueOf(dimentions[1]);
        garden = new int[initRows * initCols];
        matrix = new boolean[initRows * initCols];

        String line = reader.readLine();
        while (!line.equals("Bloom Bloom Plow")) {
            dimentions = line.split(" ");
            int row = Integer.valueOf(dimentions[0]);
            int col = Integer.valueOf(dimentions[1]);
            matrix[row * initCols + col] = true;
            line = reader.readLine();
        }
        bloom();
        printGarden();
    }

    private static void printGarden() {
        for (int row = 0; row < initRows; row++) {
            for (int col = 0; col < initCols; col++) {
                System.out.printf("%d ", garden[row * initCols + col]);
            }
            System.out.println();
        }
    }

    private static void bloom() {

        for (int row = 0; row < initRows; row++) {
            for (int col = 0; col < initCols; col++) {
                if (matrix[row * initCols + col]) {
                    //garden[row * initCols + col]++;
//                    for (int up = row - 1; up >= 0; up--) {
//                        garden[up * initCols + col]++;
//                    }
//                    for (int down = row + 1; down < initRows; down++) {
//                        garden[down * initCols + col]++;
//                    }
                    for (int innerRow = 0; innerRow < initRows; innerRow++) {
                        garden[innerRow * initCols + col]++;
                    }
                    for (int left = col - 1; left >= 0; left--) {
                        garden[row * initCols + left]++;
                    }
                    for (int right = col + 1; right < initCols; right++) {
                        garden[row * initCols + right]++;
                    }
                }
            }
        }
    }
}
