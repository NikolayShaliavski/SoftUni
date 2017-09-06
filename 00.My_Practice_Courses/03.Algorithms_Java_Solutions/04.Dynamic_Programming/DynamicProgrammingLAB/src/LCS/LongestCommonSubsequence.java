package LCS;

/**
 * Longest common subsequence (LCS) problem:
 * Given two sequences x[1 … m] and y[1 … n]
 * Find a longest common subsequence (LCS) to them both
 *
 * x = "ABCBDAB"
 * y = "BDCABA"
 * LCS = "BCBA"
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String first = "tea";
        String second = "tree";

        int[][] lcsMatrix = findLcs(first, second);
        for (int row = 0; row < lcsMatrix.length; row++) {
            for (int col = 0; col < lcsMatrix[row].length;col++) {
                System.out.print(lcsMatrix[row][col] + " ");
            }
            System.out.println();
        }
        String lcs = retrieveLcs(first, second, lcsMatrix);

        System.out.println(String.format("First string: %s", first));
        System.out.println(String.format("Second string: %s", second));
        System.out.println(String.format("Longest common subsequence: %s", lcs));
        System.out.println(String.format("Length: %d", lcs.length()));
    }

    private static int[][] findLcs(String first, String second) {

        int[][] lcsMatrix = new int[first.length() + 1][second.length() + 1];

        for (int row = 1; row < lcsMatrix.length; row++) {
            for (int col = 1; col < lcsMatrix[row].length; col++) {

                if (first.charAt(row - 1) == second.charAt(col - 1)) {
                    int diagonalCell = lcsMatrix[row - 1][col - 1];
                    lcsMatrix[row][col] = diagonalCell + 1;
                } else {
                    int upCell = lcsMatrix[row - 1][col];
                    int leftCell = lcsMatrix[row][col - 1];
                    lcsMatrix[row][col] =
                            Math.max(upCell, leftCell);
                }
            }
        }

        return lcsMatrix;
    }

    private static String retrieveLcs(String first, String second, int[][] lcsMatrix) {
        StringBuilder lcs = new StringBuilder();
        int row = lcsMatrix.length - 1;
        int col = lcsMatrix[0].length - 1;

        while (row > 0 && col > 0) {
            if (first.charAt(row - 1) == second.charAt(col - 1)) {
                lcs.insert(0, first.charAt(row - 1));
                row--;
                col--;
            } else if (lcsMatrix[row][col] == lcsMatrix[row - 1][col]) {
                row--;
            } else {
                col--;
            }
        }

        return lcs.toString();
    }
}
