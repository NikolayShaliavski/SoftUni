package pr05_BreakCycles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BreakCycles {

    private static Map<String, List<String>> graph;
    private static List<String[]> edges;
    private static List<String> result;

    public static void main(String[] args) throws IOException {
        readGraph();
        edges.sort((arr1, arr2) -> {
            int result = arr1[0].compareTo(arr2[0]);
            if (result == 0) {
                result = arr1[1].compareTo(arr2[1]);
            }
            return result;
        });
        result = new ArrayList<>();

        for (String[] edge : edges) {
            tryRemoveEdge(edge);
        }
        System.out.println("Edges to remove: " + result.size());
        for (String edge : result) {
            System.out.println(edge);
        }
    }

    private static void tryRemoveEdge(String[] edge) {
        String source = edge[0];
        String destination = edge[1];

        if (checkForCycle(source, destination)) {
            result.add(String.format("%s - %s", source, destination));
        }
    }

    private static boolean checkForCycle(String source, String destination) {
        if (!graph.get(source).contains(destination)) {
            return false;
        }
        graph.get(source).remove(destination);
        graph.get(destination).remove(source);
        Set<String> visited = new HashSet<>();
        List<String> queue = new LinkedList<>();
        queue.add(source);

        while (queue.size() > 0) {
            String vertex = queue.remove(0);
            if (vertex.equals(destination)) {
                return true;
            }
            visited.add(vertex);
            List<String> children = graph.get(vertex);
            for (String child : children) {
                if (child.equals(destination)) {
                    return true;
                }
                if (!visited.contains(child)) {
                    queue.add(child);
                }
            }
        }
        graph.get(source).add(destination);
        graph.get(destination).add(source);
        return false;
    }

    private static void readGraph() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        graph = new HashMap<>();
        edges = new ArrayList<>();

        String line = bf.readLine();

        while (!line.equals("")) {
            String[] tokens = line.split("[\\W]+");
            String parent = tokens[0];

            if (!graph.containsKey(parent)) {
                graph.put(parent, new ArrayList<>());
            }

            for (int i = 1; i < tokens.length; i++) {
                String child = tokens[i];
                graph.get(parent).add(child);

                String[] currEdge = new String[2];
                currEdge[0] = parent;
                currEdge[1] = child;
                edges.add(currEdge);
            }
            line = bf.readLine();
        }
    }
}
