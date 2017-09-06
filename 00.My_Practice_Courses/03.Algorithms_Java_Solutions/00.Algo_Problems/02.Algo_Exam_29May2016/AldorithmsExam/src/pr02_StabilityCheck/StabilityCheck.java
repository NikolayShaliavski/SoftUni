package pr02_StabilityCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StabilityCheck {

    static int buildingSize;
    static long[][] sumMatrix;

    public static void main(String[] args) throws IOException {
        readInput();

        long mostStableAreaSum = findMostStableArea();

        System.out.println(mostStableAreaSum);
    }

    private static long findMostStableArea() {
        long bestSum = Long.MIN_VALUE;
        int roof = sumMatrix.length - buildingSize;

        for (int i = 0; i <= roof; i++) {
            for (int j = 0; j <= roof; j++) {
                bestSum = Math.max(bestSum, calculateSum(i, j, i + buildingSize - 1, j + buildingSize - 1));
            }
        }
        return bestSum;
    }

    private static long calculateSum(int startRow, int starCol, int endRow, int endCol) {
        if (startRow == 0 && starCol == 0) {
            return sumMatrix[endRow][endCol];
        } else if (startRow == 0) {
            return sumMatrix[endRow][endCol] - sumMatrix[endRow][starCol - 1];
        } else if (starCol == 0) {
            return sumMatrix[endRow][endCol] - sumMatrix[startRow - 1][endCol];
        } else {
            return sumMatrix[endRow][endCol] -
                    sumMatrix[startRow - 1][endCol] -
                    sumMatrix[endRow][starCol - 1] +
                    sumMatrix[startRow - 1][starCol - 1];
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        buildingSize = Integer.valueOf(bf.readLine());
        int n = Integer.valueOf(bf.readLine());
        sumMatrix = new long[n][n];

        for (int row = 0; row < n; row++) {
            String[] currRow = bf.readLine().split("[\\s]+");
            for (int col = 0; col < n; col++) {
                int value = Integer.valueOf(currRow[col]);
                if (row == 0 && col == 0) {
                    sumMatrix[row][col] = value;
                } else if (row == 0) {
                    sumMatrix[row][col] = value + sumMatrix[row][col - 1];
                } else if (col == 0) {
                    sumMatrix[row][col] = value + sumMatrix[row - 1][col];
                } else {
                    sumMatrix[row][col] =
                            value + sumMatrix[row - 1][col] + sumMatrix[row][col - 1] -
                                    sumMatrix[row - 1][col - 1];
                }
            }
        }
    }
}
