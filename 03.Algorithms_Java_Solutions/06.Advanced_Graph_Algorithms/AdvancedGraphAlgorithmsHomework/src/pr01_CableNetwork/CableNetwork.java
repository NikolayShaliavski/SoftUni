package pr01_CableNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CableNetwork {

    private static int budget;
    private static int usedBudget;
    private static Set<Integer> connectedNodes;
    private static List<Edge> spanningTreeNodes;
    private static PriorityQueue<Edge> priorityQueue;

    public static void main(String[] args) throws IOException {
        readInput();
        usedBudget = 0;

        primMinSpanningTree();

        for (Edge edge : spanningTreeNodes) {
            System.out.println(edge.toString());
        }
        System.out.println("Budget used: " + usedBudget);
    }

    private static void primMinSpanningTree() {

        spanningTreeNodes = new ArrayList<>();
        while (priorityQueue.size() > 0) {
            Edge smallestEdge = priorityQueue.poll();

            if (usedBudget + smallestEdge.getWeight() > budget) {
                return;
            }

            if (connectedNodes.contains(smallestEdge.getSourceNode()) ^
                    connectedNodes.contains(smallestEdge.getDestinationNode())) {

                usedBudget += smallestEdge.getWeight();
                spanningTreeNodes.add(smallestEdge);

                connectedNodes.add(smallestEdge.getSourceNode());
                connectedNodes.add(smallestEdge.getDestinationNode());
            }
        }
    }

    private static void readInput() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split("[\\s]+");
        budget = Integer.valueOf(line[1]);
        line = bf.readLine().split("[\\s]+");
        int nodes = Integer.valueOf(line[1]);
        line = bf.readLine().split("[\\s]+");
        int edges = Integer.valueOf(line[1]);

        priorityQueue = new PriorityQueue<>();
        connectedNodes = new HashSet<>();
        for (int i = 0; i < edges; i++) {
            String[] edge = bf.readLine().split("[\\s]+");
            int source = Integer.valueOf(edge[0]);
            int destination = Integer.valueOf(edge[1]);

            //edge not connected
            if (edge.length == 3) {
                int weight = Integer.valueOf(edge[2]);
                priorityQueue.add(new Edge(source, destination, weight));
            } else {
                connectedNodes.add(source);
                connectedNodes.add(destination);
            }
        }
    }
}
