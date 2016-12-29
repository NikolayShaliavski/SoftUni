package dijkstra.dijkstraPriorityQueue;

import priorityQueue.PriorityQueue;

import javax.naming.OperationNotSupportedException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Dijkstra algorithm implemented with
 * priority queue(min heap)
 */
public class DijkstraPriorityQueue {

    private static Map<Integer, Node> nodes;
    private static Map<Integer, List<Edge>> graph;

    public static void main(String[] args) {
        initGraph();

        findAndPrintShortestPath(0, 9);
        findAndPrintShortestPath(0, 2);
        findAndPrintShortestPath(0, 10);
        findAndPrintShortestPath(0, 11);
        findAndPrintShortestPath(0, 1);
    }

    private static void findAndPrintShortestPath(int source, int destination) {

        System.out.print(String.format("Shortest path [%d -> %d]: ", source, destination));
        try {
            List<Integer> shortestPath = dijkstraPriorityQueue(source, destination);
            if (shortestPath == null) {
                System.out.println("No path.");
            } else {
                String formattedPath =
                        shortestPath.stream().map(Objects::toString).
                                collect(Collectors.joining("->"));
                System.out.println(String.format("%s, Length: %.0f",
                        formattedPath,
                        nodes.get(destination).getDistanceFromStart()));
            }

        } catch (OperationNotSupportedException opex) {
            System.out.println(opex.getMessage());
        }
    }

    private static List<Integer> dijkstraPriorityQueue(int source, int destination) throws
            OperationNotSupportedException {
        if (source < 0 || source > nodes.size() - 1 ||
                destination < 0 || destination > nodes.size() - 1) {
            throw new OperationNotSupportedException("Non existent node.");
        }

        int n = nodes.size();

        int[] prev = new int[n];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        boolean[] used = new boolean[n];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (Node node : nodes.values()) {
            node.setDistanceFromStart(Double.MAX_VALUE);
        }

        nodes.get(source).setDistanceFromStart(0D);
        priorityQueue.add(nodes.get(source));

        while (priorityQueue.size() > 0) {
            Node currentNode = priorityQueue.extractMin();

            if (currentNode.getId() == destination) {
                break;
            }

            for (Edge edge : graph.get(currentNode.getId())) {

                Node edgeNode = nodes.get(edge.getDestination());
                if (!used[edgeNode.getId()]) {
                    used[edgeNode.getId()] = true;
                    priorityQueue.add(edgeNode);
                }

                double newDistance = currentNode.getDistanceFromStart() + edge.getDistance();
                if (newDistance < edgeNode.getDistanceFromStart()) {
                    edgeNode.setDistanceFromStart(newDistance);
                    prev[edgeNode.getId()] = currentNode.getId();
                    priorityQueue.decreaseKey(edgeNode);
                }
            }
        }

        if (nodes.get(destination).getDistanceFromStart() == Double.MAX_VALUE) {
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

    private static void initGraph() {
        //    // 0   1   2   3   4   5   6   7   8   9  10  11
        //    { 0,  0,  0,  0,  0,  0, 10,  0, 12,  0,  0,  0 }, // 0
        //    { 0,  0,  0,  0, 20,  0,  0, 26,  0,  5,  0,  6 }, // 1
        //    { 0,  0,  0,  0,  0,  0,  0, 15, 14,  0,  0,  9 }, // 2
        //    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0 }, // 3
        //    { 0, 20,  0,  0,  0,  5, 17,  0,  0,  0,  0, 11 }, // 4
        //    { 0,  0,  0,  0,  5,  0,  6,  0,  3,  0,  0, 33 }, // 5
        //    {10,  0,  0,  0, 17,  6,  0,  0,  0,  0,  0,  0 }, // 6
        //    { 0, 26, 15,  0,  0,  0,  0,  0,  0,  3,  0, 20 }, // 7
        //    {12,  0, 14,  0,  0,  3,  0,  0,  0,  0,  0,  0 }, // 8
        //    { 0,  5,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0 }, // 9
        //    { 0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0 }, // 10
        //    { 0,  6,  9,  0, 11, 33,  0, 20,  0,  0,  0,  0 }, // 11

        nodes = new HashMap<>();
        nodes.put(0, new Node(0));
        nodes.put(1, new Node(1));
        nodes.put(2, new Node(2));
        nodes.put(3, new Node(3));
        nodes.put(4, new Node(4));
        nodes.put(5, new Node(5));
        nodes.put(6, new Node(6));
        nodes.put(7, new Node(7));
        nodes.put(8, new Node(8));
        nodes.put(9, new Node(9));
        nodes.put(10, new Node(10));
        nodes.put(11, new Node(11));

        graph = new HashMap<>();
        graph.put(0, new ArrayList<>(Arrays.asList(new Edge[]{new Edge(6, 10), new Edge(8, 12)})));
        graph.put(1, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(4, 20), new Edge(7, 26), new Edge(9, 5), new Edge(11, 6)})));
        graph.put(2, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(7, 15), new Edge(8, 14), new Edge(11, 9)})));
        graph.put(3, new ArrayList<>(Arrays.asList(new Edge[]{new Edge(10, 7)})));
        graph.put(4, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(1, 20), new Edge(5, 5), new Edge(6, 17), new Edge(11, 11)})));
        graph.put(5, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(4, 5), new Edge(6, 6), new Edge(8, 3), new Edge(11, 33)})));
        graph.put(6, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(0, 10), new Edge(4, 17), new Edge(5, 6)})));
        graph.put(7, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(1, 26), new Edge(2, 15), new Edge(9, 3), new Edge(11, 20)})));
        graph.put(8, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(0, 12), new Edge(2, 14), new Edge(5, 3)})));
        graph.put(9, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(1, 5), new Edge(7, 3)})));
        graph.put(10, new ArrayList<>(Arrays.asList(new Edge[]{new Edge(3, 7)})));
        graph.put(11, new ArrayList<>(Arrays.asList(
                new Edge[]{new Edge(1, 6), new Edge(2, 9), new Edge(4, 11), new Edge(5, 33), new Edge(7, 20)})));
    }
}
