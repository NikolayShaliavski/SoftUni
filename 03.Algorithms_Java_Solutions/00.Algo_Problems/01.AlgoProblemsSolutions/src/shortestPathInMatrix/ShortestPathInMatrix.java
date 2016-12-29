package shortestPathInMatrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestPathInMatrix {

    private static int rows;
    private static int cols;
    private static int[][] matrix;
    private static Cell[][] graph;

    public static void main(String[] args) throws IOException {
        readInput();

        List<Integer> shortestPath = findShortestPath();
        System.out.println(String.format("Length: %d", graph[rows - 1][cols - 1].getDistance()));
        System.out.print("Path: ");
        for (Integer cell : shortestPath) {
            System.out.print(cell + " ");
        }
    }

    private static List<Integer> findShortestPath() {
        boolean[][] visited = new boolean[rows][cols];

        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>();
        graph[0][0].setDistance(matrix[0][0]);
        priorityQueue.add(graph[0][0]);

        while (priorityQueue.size() > 0) {
            Cell currCell = priorityQueue.poll();

            int currRow = currCell.getRow();
            int currCol = currCell.getCol();

            if (currRow == rows - 1 && currCol == cols - 1) {
                return reconstructPath();
            }

            visited[currRow][currCol] = true;

            List<Cell> children = findChildren(currRow, currCol);

            for (Cell child : children) {
                int childRow = child.getRow();
                int childCol = child.getCol();

                int newDistance = currCell.getDistance() + matrix[childRow][childCol];
                if (newDistance <= graph[childRow][childCol].getDistance()) {
                    graph[childRow][childCol].setDistance(newDistance);
                    graph[childRow][childCol].setPrev(currCell);
                }

                if (!visited[childRow][childCol]) {
                    visited[childRow][childCol] = true;
                    priorityQueue.add(child);
                }
            }
        }

        return null;
    }

    private static List<Integer> reconstructPath() {
        List<Integer> path = new ArrayList<>();
        int row = rows - 1;
        int col = cols - 1;

        Cell cell = graph[row][col];

        while (cell != null) {
            path.add(matrix[cell.getRow()][cell.getCol()]);
            cell = cell.getPrev();
        }

        Collections.reverse(path);
        return path;
    }

    private static List<Cell> findChildren(int row, int col) {
        List<Cell> children = new ArrayList<>();

        if (row > 0) {
            children.add(graph[row - 1][col]);
        }
        if (row < rows - 1) {
            children.add(graph[row + 1][col]);
        }
        if (col > 0) {
            children.add(graph[row][col - 1]);
        }
        if (col < cols - 1) {
            children.add(graph[row][col + 1]);
        }

        return children;
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        rows = Integer.valueOf(bf.readLine());
        cols = Integer.valueOf(bf.readLine());
        matrix = new int[rows][cols];
        graph = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] columns = Arrays.stream(bf.readLine().split("[\\s]+")).
                    mapToInt(Integer::parseInt).toArray();
            for (int col = 0; col < columns.length; col++) {
                matrix[row][col] = columns[col];
                graph[row][col] = new Cell(row, col, Integer.MAX_VALUE);
            }
        }
    }
}

class Cell implements Comparable<Cell> {
    private Integer row;
    private Integer col;
    private Integer distance;
    private Cell prev;

    public Cell(Integer row, Integer col, Integer distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }

    public Integer getRow() {
        return this.row;
    }

    public Integer getCol() {
        return this.col;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Cell getPrev() {
        return this.prev;
    }

    public void setPrev(Cell prev) {
        this.prev = prev;
    }

    @Override
    public int compareTo(Cell other) {
        return this.getDistance().compareTo(other.getDistance());
    }
}
