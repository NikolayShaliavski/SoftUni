package pr07_Lumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LumberProblem {

    private static final int ROWS = 200;
    private static final int COLS = 200;

    private static BufferedReader bf;
    private static int ordersCount;
    private static Map<Integer, Lumber> lumbers;
    private static byte[][] river;

    public static void main(String[] args) throws IOException {
        readInput();

        for (int i = 0; i < ordersCount; i++) {
            String[] tokens = bf.readLine().trim().split("[\\s]+");
            int sourceId = Integer.valueOf(tokens[0]);
            int destId = Integer.valueOf(tokens[1]);

            Lumber source = lumbers.get(sourceId);
            Lumber dest = lumbers.get(destId);

            boolean foundPath = false;
            boolean[][] visited = new boolean[ROWS][COLS];
            List<Cell> queue = new LinkedList<>();
            queue.add(new Cell(source.y1, source.x1));

            while (queue.size() > 0) {
                Cell currCell = queue.remove(0);
                if (currCell.row == dest.y1 && currCell.col == dest.x1) {
                    foundPath = true;
                    break;
                }

                int row = currCell.row;
                int col = currCell.col;

                Cell up = new Cell(row - 1, col);
                Cell right = new Cell(row, col + 1);
                Cell down = new Cell(row + 1, col);
                Cell left = new Cell(row, col - 1);

                if (isInside(up) && river[up.row][up.col] == 1 &&
                        !visited[up.row][up.col]) {
                    queue.add(up);
                    visited[up.row][up.col] = true;
                }
                if (isInside(right) && river[right.row][right.col] == 1 &&
                        !visited[right.row][right.col]) {
                    queue.add(right);
                    visited[right.row][right.col] = true;
                }
                if (isInside(down) && river[down.row][down.col] == 1 &&
                        !visited[down.row][down.col]) {
                    queue.add(down);
                    visited[down.row][down.col] = true;
                }
                if (isInside(left) && river[left.row][left.col] == 1 &&
                        !visited[left.row][left.col]) {
                    queue.add(left);
                    visited[left.row][left.col] = true;
                }
            }
            if (foundPath) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isInside(Cell cell) {
        return cell.row >= 0 && cell.row < ROWS &&
                cell.col >= 0 && cell.col < COLS;
    }

    private static void readInput() throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));

        String[] line = bf.readLine().trim().split("[\\s]+");
        int lumbersCount = Integer.valueOf(line[0]);
        ordersCount = Integer.valueOf(line[1]);

        lumbers = new HashMap<>();
        river = new byte[ROWS][COLS];

        for (int i = 0; i < lumbersCount; i++) {
            line = bf.readLine().trim().split("[\\s]+");
            int x1 = Integer.valueOf(line[0]) + 100;
            int y1 = 100 - Integer.valueOf(line[1]);
            int x2 = Integer.valueOf(line[2]) + 100;
            int y2 = 100 - Integer.valueOf(line[3]);

            lumbers.put(i + 1, new Lumber(x1, y1, x2, y2));

            for (int row = y1; row <= y2; row++) {
                for (int col = x1; col <= x2; col++) {
                    river[row][col] = 1;
                }
            }
        }
    }
}

class Lumber {

    int x1;
    int y1;
    int x2;
    int y2;

    public Lumber(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

//    boolean containsPoint(int x, int y) {
////        return (this.x1 <= x && this.y1 >= y) ||
////                (this.x2 >= x && this.y2 <= y);
//        return this.x1 <= x && this.x2 >= x && this.y1 >= y && this.y2 <= y;
//    }
}

class Cell {

    int row;
    int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
