package pr02_NonCrossingBridges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NonCrossingBridges {

    static int bridgesCount;
    static int[] columns;
    static boolean[] connections;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        columns = Arrays.stream(bf.readLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        connections = new boolean[columns.length];

        findBridges();

        printBridges();
    }

    private static void findBridges() {
        //optimization -> keep indexes of elements
        //where we already have met them
        Map<Integer, Integer> lastSeen = new HashMap<>();
        for (int i = 0; i < columns.length; i++) {
            if (!lastSeen.containsKey(columns[i])) {
                lastSeen.put(columns[i], i);
            }
        }
        bridgesCount = 0;

        int lastBridgeIndex = 0;
        for (int i = 1; i < columns.length; i++) {

            //returns prev index of columns[i]
            int columnPrevIndex = lastSeen.get(columns[i]);
            //columnPrevIndex must be to the right from lastBridgeIndex to avoid crossing
            if (columnPrevIndex != i &&
                    columnPrevIndex >= lastBridgeIndex) {
                lastBridgeIndex = i;
                bridgesCount++;
                connections[i] = true;
                connections[columnPrevIndex] = true;
            }
            //not optimized solution -> for every element we search to it's left side
            //from lastBridgeIndex to i and if we meet same element -> connect new bridge
//            for (int j = lastBridgeIndex; j < i; j++) {
//
//                if (columns[j] == columns[i]) {
//                    lastBridgeIndex = i;
//                    connections[j] = true;
//                    connections[i] = true;
//                    bridgesCount++;
//                    break;
//                }
//            }

            //update index of columns[i]
            lastSeen.put(columns[i], i);
        }
    }

    private static void printBridges() {
        if (bridgesCount == 0) {
            System.out.println("No bridges found");
            for (int i = 0; i < columns.length; i++) {
                System.out.print("X ");
            }
            System.out.println();
        } else if (bridgesCount == 1) {
            System.out.println("1 bridge found");
            for (int i = 0; i < columns.length; i++) {
                if (connections[i]) {
                    System.out.print(columns[i] + " ");
                } else {
                    System.out.print("X ");
                }
            }
        } else {
            System.out.printf("%d bridges found%n", bridgesCount);
            for (int i = 0; i < columns.length; i++) {
                if (connections[i]) {
                    System.out.print(columns[i] + " ");
                } else {
                    System.out.print("X ");
                }
            }
        }
    }
}
