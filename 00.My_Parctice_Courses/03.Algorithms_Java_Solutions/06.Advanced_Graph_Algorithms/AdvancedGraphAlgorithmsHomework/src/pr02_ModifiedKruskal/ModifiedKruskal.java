package pr02_ModifiedKruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModifiedKruskal {

    private static int numberOfVerticies;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        readInput();

        List<Edge> minSpanningForest = kruskalFindSpanningForest();
        int spanningForestSum = minSpanningForest.stream().
                mapToInt(edge -> edge.getWeight()).sum();
        System.out.printf("Minimum spanning forest weight: %d%n", spanningForestSum);
        for (Edge edge : minSpanningForest) {
            System.out.println(edge.toString());
        }
    }

    private static List<Edge> kruskalFindSpanningForest() {
        List<Edge> minSpanningForest = new ArrayList<>();

        Collections.sort(edges);
        int[] parents = new int[numberOfVerticies];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (Edge edge : edges) {
            int sourceRoot = findRoot(edge.getStartNode(), parents);
            int destinationRoot = findRoot(edge.getEndNode(), parents);

            if (sourceRoot != destinationRoot) {
                minSpanningForest.add(edge);
                //mark one node as parent of other node !!!
                parents[sourceRoot] = destinationRoot;
            }
        }

        return minSpanningForest;
    }

    private static int findRoot(int node, int[] parents) {
        int root = node;

        while (parents[root] != root) {
            root = parents[root];
        }

        while (root != node) {
            int oldParent = parents[node];
            parents[node] = root;
            node = oldParent;
        }
        return root;
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split("[\\s]+");
        numberOfVerticies = Integer.valueOf(line[1]);
        line = bf.readLine().split("[\\s]+");
        int numberOfEdges = Integer.valueOf(line[1]);

        edges = new ArrayList<>();
        for (int i = 0; i < numberOfEdges; i++) {
            String[] edge = bf.readLine().split("[\\s]+");
            int source = Integer.valueOf(edge[0]);
            int destination = Integer.valueOf(edge[1]);
            int weight = Integer.valueOf(edge[2]);

            edges.add(new Edge(source, destination, weight));
        }
    }
}
