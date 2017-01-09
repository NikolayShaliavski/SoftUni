package moveDownRight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveDownRightSum {

    public static void main(String[] args) {
        int[][] cells = {
                {2, 6, 1, 8, 9, 4, 2},
                {1, 8, 0, 3, 5, 6, 7},
                {3, 4, 8, 7, 2, 1, 8},
                {0, 9, 2, 8, 1, 7, 9},
                {2, 7, 1, 9, 7, 8, 2},
                {4, 5, 6, 1, 2, 5, 6},
                {9, 3, 5, 2, 8, 1, 9},
                {2, 3, 4, 1, 7, 2, 8}
        };
        int rows = cells.length;
        int cols = cells[0].length;
        int[][] sums = new int[rows][cols];

        fillSums(cells, sums);

        List<Cell> path = recoverPath(sums);

        for (Cell cell : path) {
            System.out.println(cell.toString() +
            " Value -> " + cells[cell.getRow()][cell.getCol()]);
        }
    }

    private static void fillSums(int[][] cells, int[][] sums) {
        sums[0][0] = cells[0][0];
        for (int col = 1; col < sums[0].length; col++) {
            sums[0][col] = sums[0][col - 1] + cells[0][col];
        }
        for (int row = 1; row < sums.length; row++) {
            sums[row][0] = sums[row - 1][0] + cells[row][0];
        }

        for (int row = 1; row < sums.length; row++) {
            for (int col = 1; col < sums[row].length; col++) {
                sums[row][col] =
                        Math.max(sums[row - 1][col], sums[row][col - 1]) + cells[row][col];
            }
        }
    }

    private static List<Cell> recoverPath(int[][] sums) {
        List<Cell> path = new ArrayList<>();

        int row = sums.length - 1;
        int col = sums[0].length - 1;

        while (row > 0 && col > 0) {
            path.add(new Cell(row, col));
            if (sums[row - 1][col] > sums[row][col - 1]) {
                row--;
            } else {
                col--;
            }
        }
        while (row > 0){
            path.add(new Cell(row, col));
            row--;
        }
        while (col > 0){
            path.add(new Cell(row, col));
            col--;
        }
        path.add(new Cell(0, 0));
        Collections.reverse(path);
        return path;
    }
}
