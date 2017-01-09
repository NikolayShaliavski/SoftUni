package minimumSpanningTree.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Time complexity: O(|E| * log* |E|)
 */
public class Kruskal {

    public static void main(String[] args) {

        int numberOfVertices = 9;
        List<Edge> graphEdges =
                new ArrayList<>(Arrays.asList(
                        new Edge(0, 3, 9),
                        new Edge(0, 5, 4),
                        new Edge(0, 8, 5),
                        new Edge(1, 4, 8),
                        new Edge(1, 7, 7),
                        new Edge(2, 6, 12),
                        new Edge(3, 5, 2),
                        new Edge(3, 6, 8),
                        new Edge(3, 8, 20),
                        new Edge(4, 7, 10),
                        new Edge(6, 8, 7)));

        List<Edge> minimumSpanningForest = kruskalAlgorithm(numberOfVertices, graphEdges);

        System.out.println("Minimum spanning forest: ");
        for (Edge edge : minimumSpanningForest) {
            System.out.println(edge);
        }
    }

    private static List<Edge> kruskalAlgorithm(int numberOfVertices, List<Edge> graphEdges) {
        /**
         * Sorting edges by their weight descending
         */
        Collections.sort(graphEdges);

        /**
         * For each node we will keep track of it's parent
         * here nodes are mapped to numbers (indexes in array)
         * In the beginning every node is parent to himself
         */
        int[] parents = new int[numberOfVertices];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        List<Edge> minSpanForest = new ArrayList<>();
        for (Edge edge : graphEdges) {
            int startNodeRoot = findRoot(edge.getStartNode(), parents);
            int endEdgeRoot = findRoot(edge.getEndNode(), parents);

            if (startNodeRoot != endEdgeRoot) {
                minSpanForest.add(edge);
                //mark one node as parent of other node !!!
                parents[startNodeRoot] = endEdgeRoot;
            }
        }

        return minSpanForest;
    }

    private static int findRoot(int node, int[] parents) {
        int root = node;
        while (parents[root] != root) {
            root = parents[root];
        }
        //Optimize the path to root
        //Attach each path node directly to the root
        while (root != node) {
            int oldParent = parents[node];
            parents[node] = root;
            node = oldParent;
        }
        return root;
    }
}
