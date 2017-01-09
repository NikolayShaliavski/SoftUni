package topologicalSorting;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopSortSourceRemoval {

    private static Map<String, List<String>> graph;

    public static void main(String[] args) throws IOException {

        readGraph();
        Map<String, Integer> predecessorsCount = findPredecessors();
        List<String> removedVertexes = new ArrayList<>();

        while (true) {
            String nodeToRemove =
                    graph.keySet().stream().
                            filter(child -> predecessorsCount.get(child) == 0).
                            findFirst().orElse(null);
            if (nodeToRemove == null) {
                break;
            }
            for (String child : graph.get(nodeToRemove)) {
                predecessorsCount.put(child, predecessorsCount.get(child) - 1);
            }
            removedVertexes.add(nodeToRemove);
            graph.remove(nodeToRemove);
        }

        if (graph.size() > 0) {
            try {
                throw new OperationNotSupportedException("A cycle detected in the graph.");
            } catch (OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Topological sorting: ");
            for (String removedVertex : removedVertexes) {
                System.out.print(removedVertex + " ");
            }
            System.out.println();
        }
    }

    private static Map<String, Integer> findPredecessors() {
        Map<String, Integer> predecessorsCount = new HashMap<>();

        for (Map.Entry<String, List<String>> vertex : graph.entrySet()) {
            if (!predecessorsCount.containsKey(vertex.getKey())) {
                predecessorsCount.put(vertex.getKey(), 0);
            }
            for (String child : vertex.getValue()) {
                if (!predecessorsCount.containsKey(child)) {
                    predecessorsCount.put(child, 0);
                }
                predecessorsCount.put(child, predecessorsCount.get(child) + 1);
            }
        }
        return predecessorsCount;
    }

    private static void readGraph() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        graph = new HashMap<>();

        String line = bf.readLine();
        while (!line.equals("")) {
            String[] tokens = line.split("[\"|\\-|>|\\s|\\,]+");

            if (!graph.containsKey(tokens[1])) {
                graph.put(tokens[1], new ArrayList<>());
            }
            for (int i = 2; i < tokens.length; i++) {
                graph.get(tokens[1]).add(tokens[i]);
            }
            line = bf.readLine();
        }
    }
}
