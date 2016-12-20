package pr07_ConnectingCables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectingCables {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String sideOneStr = bf.readLine();
        String sideTwoStr = bf.readLine();

        String[] sideOne = parseInput(sideOneStr);
        String[] sideTwo = parseInput(sideTwoStr);

        int[][] connectionsMatrix = findPossibleConnections(sideOne, sideTwo);

        String connections = retrieveConnections(sideOne, sideTwo, connectionsMatrix);
        int connectedPairs =
                connectionsMatrix[connectionsMatrix.length - 1][connectionsMatrix[0].length - 1];

        System.out.println(
                String.format("Maximum pairs connected: %d", connectedPairs));
        System.out.println(String.format("Connected pairs: %s", connections));
    }

    private static int[][] findPossibleConnections(String[] sideOne, String[] sideTwo) {
        int[][] lcsMatrix = new int[sideOne.length + 1][sideTwo.length + 1];

        for (int row = 1; row < lcsMatrix.length; row++) {
            for (int col = 1; col < lcsMatrix[row].length; col++) {

                if (sideOne[row - 1].equals(sideTwo[col - 1])) {
                    int diagonalCell = lcsMatrix[row - 1][col - 1];
                    lcsMatrix[row][col] = diagonalCell + 1;
                } else {
                    int upCell = lcsMatrix[row - 1][col];
                    int leftCell = lcsMatrix[row][col - 1];
                    lcsMatrix[row][col] = Math.max(upCell, leftCell);
                }
            }
        }

        return lcsMatrix;
    }

    private static String retrieveConnections(
            String[] sideOne, String[] sideTwo, int[][] connectionsMatrix) {
        StringBuilder connections = new StringBuilder();
        int row = connectionsMatrix.length - 1;
        int col = connectionsMatrix[0].length - 1;

        while (row > 0 && col > 0) {
            if (sideOne[row - 1].equals(sideTwo[col - 1])) {
                connections.insert(0, sideOne[row - 1] + " ");
                row--;
                col--;
            } else if (connectionsMatrix[row][col] == connectionsMatrix[row - 1][col]) {
                row--;
            } else {
                col--;
            }
        }

        return connections.toString().trim();
    }

    private static String[] parseInput(String side) {
        side = side.substring(side.indexOf('{') + 1, side.indexOf('}'));
        String[] sideArr = side.split("\\,");
        return sideArr;
    }
}
