package pr06_MinimumEditDistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumEditDistance {

    private static int replaceCost;
    private static int insertCost;
    private static int deleteCost;

    private static int minEditDist;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String replace = bf.readLine();
        String insert = bf.readLine();
        String delete = bf.readLine();

        replaceCost = Integer.valueOf(replace.charAt(replace.length() - 1) + "");
        insertCost = Integer.valueOf(insert.charAt(insert.length() - 1) + "");
        deleteCost = Integer.valueOf(delete.charAt(delete.length() - 1) + "");

        String first = bf.readLine().substring(5);
        String second = bf.readLine().substring(5);

        minEditDist = 0;

        int[][] reverseLcsMatrix = findLcs(first, second);
        //printMatrix(reverseLcs);
        String actions = retrieveOperations(reverseLcsMatrix, first, second);
        System.out.println("Minimum edit distance: " + minEditDist);
        System.out.println(actions);
    }

    private static String retrieveOperations(int[][] reverseLcsMatrix, String first, String second) {
        StringBuilder sb = new StringBuilder();
        int row = reverseLcsMatrix.length - 1;
        int col = reverseLcsMatrix[0].length - 1;

        while (row > 0 && col > 0) {
            if (first.charAt(row - 1) == second.charAt(col - 1)) {
                row--;
                col--;
            } else if (reverseLcsMatrix[row][col] == reverseLcsMatrix[row - 1][col - 1] + 1) {
                if (replaceCost <= (deleteCost + insertCost)) {
                    minEditDist += replaceCost;
                    sb.insert(0, String.format("REPLACE(%d, %s)%n", row - 1, second.charAt(col - 1)));
                } else {
                    minEditDist += deleteCost;
                    sb.insert(0, String.format("DELETE(%d)%n", row - 1));
                    minEditDist += insertCost;
                    sb.append(String.format("INSERT(%d, %s)%n", row - 1, second.charAt(col - 1)));
                }
                row--;
                col--;
            } else if (reverseLcsMatrix[row][col] == reverseLcsMatrix[row][col - 1] + 1) {
                minEditDist += insertCost;
                sb.insert(0, String.format("INSERT(%d, %s)%n", row - 1, second.charAt(col - 1)));
                col--;
            } else if (reverseLcsMatrix[row][col] == reverseLcsMatrix[row - 1][col] + 1) {
                minEditDist += deleteCost;
                sb.insert(0, String.format("DELETE(%d)%n", row - 1));
                row--;
            }
        }
        while (col > 0) {
            minEditDist += insertCost;
            sb.insert(0, String.format("INSERT(%d, %s)%n", col - 1, second.charAt(col - 1)));
            col--;
        }
        while (row > 0) {
            minEditDist += deleteCost;
            sb.insert(0, String.format("DELETE(%d)%n", row - 1));
            row--;
        }
        return sb.toString().trim();
    }

    private static int[][] findLcs(String first, String second) {
        int[][] reverseLcs = new int[first.length() + 1][second.length() + 1];

        //fill first row && column with increased values
        for (int row = 0; row < reverseLcs.length; row++) {
            reverseLcs[row][0] = row;
        }
        for (int col = 0; col < reverseLcs[0].length; col++) {
            reverseLcs[0][col] = col;
        }

        /**
         *      f  i  r  s  t
         *   0  1  2  3  4  5
         * s 1  0  ...etc
         * e 2  0
         * c 3
         * o 4
         * n 5
         * d 6
         * If elements match -> we write already calculated diagonal
         * unlike LCS problem where if more elements match -> diagonal++
         * If elements don't match -> we write min value from
         * upCell, leftCell, diagonal.
         * After that we retrace matrix to see which operations to perform
         */

        for (int row = 1; row < reverseLcs.length; row++) {
            for (int col = 1; col < reverseLcs[row].length; col++) {

                int diagonal = reverseLcs[row - 1][col - 1];
                if (first.charAt(row - 1) == second.charAt(col - 1)) {
                    reverseLcs[row][col] = diagonal;
                } else {
                    int upCell = reverseLcs[row - 1][col];
                    int leftCell = reverseLcs[row][col - 1];
                    reverseLcs[row][col] = Math.min(Math.min(upCell, leftCell), diagonal) + 1;
                }
            }
        }

        return reverseLcs;
    }

    private static void printMatrix(int[][] reverseLcs) {
        for (int row = 0; row < reverseLcs.length; row++) {
            for (int col = 0; col < reverseLcs[row].length; col++) {
                System.out.print(reverseLcs[row][col] + " ");
            }
            System.out.println();
        }
    }
}
