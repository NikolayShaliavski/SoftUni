package pr04_FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {

    private static List<Edge> edges;
    private static int numberOfVertices;
    private static Double[][] distances;

    public static void main(String[] args) throws IOException {
        readInput();

        floydWarshallAlgorithm();

        System.out.println("Shortest paths matrix:");
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.print("---");
        }
        System.out.println();
        printDistances();
    }

    private static void floydWarshallAlgorithm() {

        for (int k = 0; k < numberOfVertices; k++) {
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
    }

    private static void printDistances() {
        for (int row = 0; row < distances.length; row++) {
            for (int col = 0; col < distances[row].length; col++) {
                System.out.printf("%3.0f", distances[row][col]);
            }
            System.out.println();
        }
    }

    private static void readInput() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split("[\\s]+");
        numberOfVertices = Integer.valueOf(line[1]);
        line = bf.readLine().split("[\\s]+");
        int numberOfEdges = Integer.valueOf(line[1]);

        edges = new ArrayList<>();
        distances = new Double[numberOfVertices][numberOfVertices];
        for (int row = 0; row < numberOfVertices; row++) {
            for (int col = 0; col < numberOfVertices; col++) {
                if (row == col) {
                    distances[row][col] = 0D;
                } else {
                    distances[row][col] = Double.POSITIVE_INFINITY;
                }
            }
        }

        for (int i = 0; i < numberOfEdges; i++) {
            String[] edge = bf.readLine().split("[\\s]+");
            int source = Integer.valueOf(edge[0]);
            int destination = Integer.valueOf(edge[1]);
            Double weight = Double.valueOf(edge[2]);

            //add edge to edges list
            edges.add(new Edge(source, destination, weight));

            //add edge to distances matrix(ajacency matrix)
            distances[source][destination] = weight;
            distances[destination][source] = weight;
        }
    }
}
