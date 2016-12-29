package pr02_MostReliablePath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MostReliablePath {

    private static double reliability;
    private static double[][] graph;
    private static int nodes;
    private static int source;
    private static int destination;

    public static void main(String[] args) throws IOException {
        readInput();
        List<Integer> path = dijkstraFindPath();

        System.out.printf("Most reliable path reliability: %.2f", reliability);
        System.out.println("%");
        String formattedPath =
                path.stream().map(Objects::toString).
                        collect(Collectors.joining(" -> "));
        System.out.println(formattedPath);
    }

    private static List<Integer> dijkstraFindPath() {
        double[] reliabilities = new double[nodes];
        for (int i = 0; i < reliabilities.length; i++) {
            reliabilities[i] = Double.MIN_VALUE;
        }
        int[] prev = new int[nodes];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        boolean[] visited = new boolean[nodes];

        reliabilities[source] = 1;
        while (true) {
            double maxReliability = Double.MIN_VALUE;
            int maxNode = 0;

            for (int node = 0; node < reliabilities.length; node++) {
                if (!visited[node] && reliabilities[node] >= maxReliability) {
                    maxReliability = reliabilities[node];
                    maxNode = node;
                }
            }

            if (maxReliability == Double.MIN_VALUE) {
                break;
            }

            visited[maxNode] = true;

            for (int i = 0; i < nodes; i++) {

                if (graph[maxNode][i] != 0) {
                    double newReliability = maxReliability * graph[maxNode][i];
                    if (newReliability > reliabilities[i]) {
                        reliabilities[i] = newReliability;
                        prev[i] = maxNode;
                    }
                }
            }
        }

        if (reliabilities[destination] == Double.MIN_VALUE) {
            return null;
        }
        reliability = reliabilities[destination] * 100;
        return reconstructPath(prev);
    }

    private static List<Integer> reconstructPath(int[] prev) {
        List<Integer> path = new ArrayList<>();

        int nodeIndex = destination;
        while (nodeIndex != -1) {
            path.add(nodeIndex);
            nodeIndex = prev[nodeIndex];
        }

        Collections.reverse(path);
        return path;
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = bf.readLine().split("[\\s]+");
        nodes = Integer.valueOf(tokens[1]);
        tokens = bf.readLine().split("[\\s]+");
        source = Integer.valueOf(tokens[1]);
        destination = Integer.valueOf(tokens[3]);
        tokens = bf.readLine().split("[\\s]+");
        int edges = Integer.valueOf(tokens[1]);

        graph = new double[nodes][nodes];
        for (int i = 0; i < edges; i++) {
            tokens = bf.readLine().split("[\\s]+");
            int sourceNode = Integer.valueOf(tokens[0]);
            int destinationNode = Integer.valueOf(tokens[1]);
            double weight = Double.valueOf(tokens[2]) / 100;

            //undirected graph so edge is
            //from source -> destination
            //and from destination -> source
            graph[sourceNode][destinationNode] = weight;
            graph[destinationNode][sourceNode] = weight;
        }
    }
}
