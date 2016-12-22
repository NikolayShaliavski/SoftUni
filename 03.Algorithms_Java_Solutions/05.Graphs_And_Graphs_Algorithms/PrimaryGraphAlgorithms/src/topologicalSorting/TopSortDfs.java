package topologicalSorting;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopSortDfs {

    private static Map<String, List<String>> graph;
    private static Set<String> cycleVertexes;
    private static Set<String> visited;
    private static List<String> sortedVertexes;

    public static void main(String[] args) throws IOException, OperationNotSupportedException {
        readGraph();

        sortedVertexes = new LinkedList<>();
        visited = new HashSet<>();
        cycleVertexes = new HashSet<>();

        for (String vertex : graph.keySet()) {
            topDfsSort(vertex);
        }
        System.out.println("Topological sorting: ");
        for (String sortedVertex : sortedVertexes) {
            System.out.print(sortedVertex + " ");
        }
    }

    private static void topDfsSort(String vertex) throws OperationNotSupportedException {
        if (cycleVertexes.contains(vertex)) {
            throw new OperationNotSupportedException("A cycle detected in the graph.");
        }
        if (!visited.contains(vertex)) {
            visited.add(vertex);
            //add vertex to cycleSet to watch
            //if there is a cycle in graph (child has edge to his parent)
            cycleVertexes.add(vertex);

            for (String child : graph.get(vertex)) {
                topDfsSort(child);
            }
            sortedVertexes.add(0, vertex);
            //mandatory remove vertex in post-action
            cycleVertexes.remove(vertex);
        }
    }

    private static void readGraph() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        graph = new HashMap<>();

        String inputLine = bf.readLine();
        while (!inputLine.equals("")) {
            String[] tokens = inputLine.split("[\"|\\-|>|\\s|\\,]+");

            if (!graph.containsKey(tokens[1])) {
                graph.put(tokens[1], new ArrayList<>());
            }
            for (int i = 2; i < tokens.length; i++) {
                graph.get(tokens[1]).add(tokens[i]);
            }
            inputLine = bf.readLine();
        }
    }
}
