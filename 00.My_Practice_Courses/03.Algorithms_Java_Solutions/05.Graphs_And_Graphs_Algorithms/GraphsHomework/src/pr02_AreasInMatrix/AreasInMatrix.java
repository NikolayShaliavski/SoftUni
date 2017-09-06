package pr02_AreasInMatrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class AreasInMatrix {

    private static char[][] matrix;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int rowsCount = Integer.valueOf(line.charAt(line.length() - 1) + "");

        matrix = new char[rowsCount][];
        visited = new boolean[rowsCount][];
        for (int row = 0; row < rowsCount; row++) {
            String column = bf.readLine();

            matrix[row] = new char[column.length()];
            visited[row] = new boolean[column.length()];

            for (int col = 0; col < column.length(); col++) {
                matrix[row][col] = column.charAt(col);
            }
        }

        Map<Character, Integer> connectedAreas = new LinkedHashMap<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!visited[row][col]) {
                    dfs(row, col, matrix[row][col]);
                    if (!connectedAreas.containsKey(matrix[row][col])) {
                        connectedAreas.put(matrix[row][col], 0);
                    }
                    connectedAreas.put(matrix[row][col], connectedAreas.get(matrix[row][col]) + 1);
                }
            }
        }
        int allAreas = connectedAreas.entrySet().stream().
                mapToInt(area -> area.getValue()).sum();
        System.out.println("Areas: " + allAreas);
        for (Map.Entry<Character,Integer> area : connectedAreas.entrySet()) {
            System.out.println(String.format("Letter '%s' -> %d", area.getKey(), area.getValue()));
        }
    }

    private static void dfs(int row, int col, char prevCell) {
        //outside row bounds
        if (row < 0 || row > matrix.length - 1) {
            return;
        }
        //outside col bounds
        if (col < 0 || col > matrix[row].length - 1) {
            return;
        }
        //if we have already visited this cell
        if (visited[row][col]) {
           return;
        }
        //if previous cell is different
        if (matrix[row][col] != prevCell) {
            return;
        }
        //mark cell as visited
        visited[row][col] = true;

        dfs(row - 1, col, matrix[row][col]);//up
        dfs(row + 1, col, matrix[row][col]);//down
        dfs(row, col - 1, matrix[row][col]);//left
        dfs(row, col + 1, matrix[row][col]);//right
    }
}
