package pr05_BellmanFord;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BellmanFord {

    private static List<Edge> edges;
    private static int numberOfVertices;
    private static int source;
    private static int destination;
    private static Double distance;

    public static void main(String[] args) throws IOException {
        readInput();

        try {
            List<Integer> shortestPath = bellmanFordAlgorithm();

            String formattedPath = shortestPath.stream().
                    map(Objects::toString).collect(Collectors.joining(" -> "));
            System.out.printf("Distance [%d -> %d]: %.0f%n", source, destination, distance);
            System.out.printf("Path: %s%n", formattedPath);
        } catch (OperationNotSupportedException opex) {
            System.out.println(opex.getMessage());
        }
    }

    private static List<Integer> bellmanFordAlgorithm() throws OperationNotSupportedException {
        Double[] distances = new Double[numberOfVertices];
        int[] prev = new int[numberOfVertices];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1;
        }
        
        distances[source] = 0D;

        for (int i = 1; i < numberOfVertices; i++) {
            for (Edge edge : edges) {
                int source = edge.getSource();
                int destination = edge.getDestination();
                Double weight = edge.getWeight();

                if (distances[source] + weight < distances[destination]) {
                    distances[destination] = distances[source] + weight;
                    prev[destination] = source;
                }
            }
        }
        //last iteration over edges to check for negative cycles
        for (Edge edge : edges) {
            int source = edge.getSource();
            int destination = edge.getDestination();
            Double weight = edge.getWeight();

            //if there is edge to relax -> it's cycle
            if (distances[source] + weight < distances[destination]) {
                List<Integer> cycle = new ArrayList<>();
                cycle.add(source);
                int cycleNode = prev[source];
                //reconstruct cycle via prev array
                //if we return again in source node -> this is the end (beginning) of the cycle
                while (cycleNode != source) {
                    cycle.add(cycleNode);
                    cycleNode = prev[cycleNode];
                }
                Collections.reverse(cycle);
                String formattedPath = cycle.stream().
                        map(Objects::toString).collect(Collectors.joining(" -> "));
                throw new OperationNotSupportedException("Negative cycle detected: " + formattedPath);
            }
        }
        distance = distances[destination];
        if (distance == Double.POSITIVE_INFINITY) {
            throw new OperationNotSupportedException("No path.");
        }
        return reconstructPath(prev);
    }

    private static List<Integer> reconstructPath(int[] prev) {
        List<Integer> path = new ArrayList<>();

        int node = destination;
        path.add(node);

        while (prev[node] != -1) {
            node = prev[node];
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    private static void readInput() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = bf.readLine().split("[\\s]+");
        numberOfVertices = Integer.valueOf(tokens[1]);

        tokens = bf.readLine().split("[\\s]+");
        source = Integer.valueOf(tokens[1]);
        destination = Integer.valueOf(tokens[3]);

        tokens = bf.readLine().split("[\\s]+");
        int numberOfEdges = Integer.valueOf(tokens[1]);

        edges = new ArrayList<>();
        for (int i = 0; i < numberOfEdges; i++) {
            String[] edge = bf.readLine().split("[\\s]+");
            int source = Integer.valueOf(edge[0]);
            int destination = Integer.valueOf(edge[1]);
            Double weight = Double.valueOf(edge[2]);

            edges.add(new Edge(source, destination, weight));
        }
    }
}
