package pr03_GraphCicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GraphCycles {

    static Set<Integer>[] graph;
    static boolean[][] hasEdge;
    static List<String> cycles;

    public static void main(String[] args) throws IOException {
        readInput();
        cycles = new ArrayList<>();
        int nodes = graph.length;
        for (int u = 0; u < nodes; u++) {
            for (Integer v : graph[u]) {
                if (v > u) {
                    for (Integer w : graph[v]) {
                        if (w > u && w != v) {
                            if (hasEdge[w][u]) {
                                cycles.add(String.format("{%d -> %d -> %d}", u, v, w));
                            }
                        }
                    }
                }
            }
        }
        if (cycles.size() > 0) {
            for (String cycle : cycles) {
                System.out.println(cycle);
            }
        } else {
            System.out.println("No cycles found");
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.valueOf(bf.readLine());
        graph = new Set[nodesCount];
        hasEdge = new boolean[nodesCount][nodesCount];

        for (int i = 0; i < nodesCount; i++) {
            String[] tokens = bf.readLine().split("[\\s\\-\\>]+");
            int[] nodes = new int[tokens.length];

            for (int j = 0; j < nodes.length; j++) {
                nodes[j] = Integer.valueOf(tokens[j]);
            }

            int source = nodes[0];
            graph[source] = new TreeSet<>();
            for (int j = 1; j < tokens.length; j++) {
                int destination = nodes[j];
                graph[source].add(destination);
                hasEdge[source][destination] = true;
            }
        }
    }
}
