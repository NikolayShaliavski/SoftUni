package pr01_Distances;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistancesBetweenVertices {

    private static Map<Integer, List<Integer>> graph;
    private static List<int[]> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        readGraph(bf);
        readEdges(bf);

        for (int[] edge : edges) {
            int source = edge[0];
            int destination = edge[1];
            findDistance(source, destination);
        }
        System.out.println();
    }

    private static void findDistance(int source, int destination) {
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> queue = new LinkedList<>();

        for (Integer vertex : graph.keySet()) {
            distances.put(vertex, -1);
        }

        queue.add(source);
        visited.add(source);
        distances.put(source, 0);

        while (true) {
            if (queue.size() == 0) {
                break;
            }
            int currVertex = queue.remove(0);
            if (currVertex == destination) {
                break;
            }
            List<Integer> children = graph.get(currVertex);
            for (Integer child : children) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    queue.add(child);
                    distances.put(child, distances.get(currVertex) + 1);
                }
            }
        }
        printDistance(source, destination, distances);
    }

    private static void printDistance(int source, int destination, Map<Integer, Integer> distances) {
        System.out.println(
                String.format("{%d, %d} -> %d", source, destination, distances.get(destination)));
    }

    private static void readGraph(BufferedReader bf) throws IOException {
        graph = new HashMap<>();
        String line = bf.readLine();
        line = bf.readLine();
        while (!line.equals("Distances to find:")) {
            int[] vertices = Arrays.stream(line.split("[\\D]+")).
                    mapToInt(Integer::parseInt).toArray();
            int vertex = vertices[0];
            if (!graph.containsKey(vertex)) {
                graph.put(vertex, new ArrayList<>());
            }
            for (int i = 1; i < vertices.length; i++) {
                graph.get(vertex).add(vertices[i]);
            }

            line = bf.readLine();
        }
    }

    private static void readEdges(BufferedReader bf) throws IOException {
        edges = new ArrayList<>();
        String line = bf.readLine();
        while (!line.equals("")) {
            int[] vertices = Arrays.stream(line.split("\\-")).
                    mapToInt(Integer::parseInt).toArray();
            edges.add(vertices);
            line = bf.readLine();
        }
    }
}
