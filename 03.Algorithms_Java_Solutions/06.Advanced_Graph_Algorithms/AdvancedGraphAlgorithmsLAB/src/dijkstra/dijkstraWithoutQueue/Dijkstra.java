package dijkstra.dijkstraWithoutQueue;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Dijkstra algorithm to find shortest path
 * between two nodes, implemented without priority queue
 */
public class Dijkstra {

    private static int[][] graph = {
          // 0  1  2  3  4  5  6   7   8  9  10 11
            {0, 0, 0, 0, 0, 0, 10, 0, 12, 0, 0, 0}, // 0
            {0, 0, 0, 0, 20, 0, 0, 26, 0, 5, 0, 6}, // 1
            {0, 0, 0, 0, 0, 0, 0, 15, 14, 0, 0, 9}, // 2
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0}, // 3
            {0, 20, 0, 0, 0, 5, 17, 0, 0, 0, 0, 11}, // 4
            {0, 0, 0, 0, 5, 0, 6, 0, 3, 0, 0, 33}, // 5
            {10, 0, 0, 0, 17, 6, 0, 0, 0, 0, 0, 0}, // 6
            {0, 26, 15, 0, 0, 0, 0, 0, 0, 3, 0, 20}, // 7
            {12, 0, 14, 0, 0, 3, 0, 0, 0, 0, 0, 0}, // 8
            {0, 5, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0}, // 9
            {0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0}, // 10
            {0, 6, 9, 0, 11, 33, 0, 20, 0, 0, 0, 0}, // 11
    };

    public static void main(String[] args) {

        findAndPrintShortestPath(9, 0);
    }

    private static void findAndPrintShortestPath(int source, int destination) {

        System.out.print(String.format("Shortest path [%d -> %d]: ", source, destination));
        try {
            List<Integer> shortestPath = dijkstraAlgorithm(source, destination);

            if (shortestPath == null) {
                System.out.println("No path.");
            } else {
                int pathLength = 0;
                for (int i = 0; i < shortestPath.size() - 1; i++) {
                    pathLength += graph[shortestPath.get(i)][shortestPath.get(i + 1)];
                }
                String formattedPath =
                        shortestPath.stream().map(Objects::toString).
                                collect(Collectors.joining("->"));
                System.out.println(String.format("%s, Length: %d", formattedPath, pathLength));
            }
        } catch (OperationNotSupportedException opex) {
            System.out.println(opex.getMessage());
        }
    }

    private static List<Integer> dijkstraAlgorithm(int source, int destination) throws OperationNotSupportedException {
        if (source < 0 || source > graph.length - 1 ||
                destination < 0 || destination > graph.length - 1) {
            throw new OperationNotSupportedException("Non existent node.");
        }
        int n = graph.length;
        //init distances to each node
        int[] distances = new int[n];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        //distance to source node is 0
        distances[source] = 0;

        //prev array to reconstruct path -> for each node we keep it's previous
        int[] prev = new int[n];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }

        //array to keep track of already visited nodes
        boolean[] visited = new boolean[n];

        while (true) {
            //find the nearest unvisited node from the source
            int minDistance = Integer.MAX_VALUE;
            int minNode = 0;
            for (int node = 0; node < n; node++) {
                if (!visited[node] && distances[node] < minDistance) {
                    minDistance = distances[node];
                    minNode = node;
                }
            }
            //no min distance node found -> algorithm finished
            if (minDistance == Integer.MAX_VALUE) {
                break;
            }
            //mark it as visited
            visited[minNode] = true;

            //Improve distances[0...n-1] through minNode
            for (int i = 0; i < n; i++) {

                if (graph[minNode][i] > 0) {//current node is connected to minNode
                    //calculate newDistance ->
                    //(best distance to minNode + distance from minNode to currNode)
                    int newDistance = minDistance + graph[minNode][i];
                    if (newDistance < distances[i]) {
                        //we found better distance to current node
                        //update distance && it's previous
                        distances[i] = newDistance;
                        prev[i] = minNode;
                    }
                }
            }
        }

        if (distances[destination] == Integer.MAX_VALUE) {
            //No path found from source to destination node
            return null;
        }

        return reconstructPath(destination, prev);
    }

    private static List<Integer> reconstructPath(int node, int[] prev) {
        List<Integer> path = new ArrayList<>();

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }
        Collections.reverse(path);
        return path;
    }
}
