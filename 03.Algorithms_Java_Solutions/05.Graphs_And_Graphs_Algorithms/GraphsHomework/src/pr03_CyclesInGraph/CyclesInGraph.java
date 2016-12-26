package pr03_CyclesInGraph;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyclesInGraph {

    private static Map<String, List<String>> graph;
    private static Set<String> visited;
    private static Set<String> cycleVertices;

    public static void main(String[] args) throws IOException {
        readGraph();

        try {
            for (String vertex : graph.keySet()) {
                if (!visited.contains(vertex)) {
                    dfs(vertex, null);
                }
            }
            System.out.println("Acyclic: Yes");
        } catch (OperationNotSupportedException e) {
            System.out.println("Acyclic: No");
        }
    }

    private static void dfs(String vertex, String parent) throws OperationNotSupportedException {
        visited.add(vertex);
        List<String> children = graph.get(vertex);
        for (String child : children) {
            if (visited.contains(child) && parent != null && !parent.equals(child)) {
                throw new OperationNotSupportedException();
            }
            if (!visited.contains(child)) {
                dfs(child, vertex);
            }
        }
    }

    private static void readGraph() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        graph = new HashMap<>();
        visited = new HashSet<>();
        cycleVertices = new HashSet<>();

        String line = bf.readLine();
        while (!line.equals("")) {
            String[] tokens = line.split("[\\W]+");
            String parent = tokens[0];
            String child = tokens[1];
            if (!graph.containsKey(parent)) {
                graph.put(parent, new ArrayList<>());
            }
            if (!graph.containsKey(child)) {
                graph.put(child, new ArrayList<>());
            }
            //graph is undirected so we add child to parent and
            //parent to child - there are edges in two directions
            //if one vertex is child of another vertex (parent),
            //parent is also child of his child
            graph.get(parent).add(child);
            graph.get(child).add(parent);

            line = bf.readLine();
        }
    }
}
