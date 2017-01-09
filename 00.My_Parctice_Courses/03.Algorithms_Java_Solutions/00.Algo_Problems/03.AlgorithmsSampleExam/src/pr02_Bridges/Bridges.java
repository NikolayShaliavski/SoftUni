package pr02_Bridges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Like longest common subsequence but with recursion
 * Also from each element are allowed more than one connection
 *     3
 *  3  3  3 -> here are 3 connections
 */
public class Bridges {

    static final int NOT_CALCULATED = -1;

    static int[] north;
    static int[] south;
    static int[][] connections;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        north = Arrays.stream(bf.readLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        south = Arrays.stream(bf.readLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();

        connections = new int[north.length][south.length];
        for (int row = 0; row < connections.length; row++) {
            for (int col = 0; col < connections[row].length; col++) {
                connections[row][col] = NOT_CALCULATED;
            }
        }

        int maxConnections = connectBridges(north.length - 1, south.length - 1);
        System.out.println(maxConnections);
    }

    private static int connectBridges(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }
        //memoization
        if (connections[x][y] != NOT_CALCULATED) {
            return connections[x][y];
        }
        int northLeft = connectBridges(x - 1, y);
        int southLeft = connectBridges(x, y - 1);
        if (north[x] == south[y]) {
            connections[x][y] = 1 + Math.max(northLeft, southLeft);
        } else {
            connections[x][y] = Math.max(northLeft, southLeft);
        }
        return connections[x][y];
    }
}
