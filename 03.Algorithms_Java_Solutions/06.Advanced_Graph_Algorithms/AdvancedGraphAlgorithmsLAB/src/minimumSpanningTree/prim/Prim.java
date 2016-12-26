package minimumSpanningTree.prim;

import priorityQueue.PriorityQueue;

import java.util.*;

public class Prim {

//    private static List<Edge> edges =
//            new ArrayList<>(Arrays.asList(
//                    new Edge("C", "D", 20),
//                    new Edge("D", "B", 2),
//                    new Edge("D", "E", 8),
//                    new Edge("I", "H", 7),
//                    new Edge("B", "A", 4),
//                    new Edge("C", "E", 7),
//                    new Edge("G", "H", 8),
//                    new Edge("I", "G", 10),
//                    new Edge("A", "D", 9),
//                    new Edge("E", "F", 12),
//                    new Edge("C", "A", 5)
//            ));

    private static List<Edge> edges = new ArrayList<>(Arrays.asList(
            new Edge("0", "3", 9),
            new Edge("0", "5", 4),
            new Edge("0", "8", 5),
            new Edge("1", "4", 8),
            new Edge("1", "7", 7),
            new Edge("2", "6", 12),
            new Edge("3", "5", 2),
            new Edge("3", "6", 8),
            new Edge("3", "8", 20),
            new Edge("4", "7", 10),
            new Edge("6", "8", 7)));

    private static Map<String, List<Edge>> graph;
    private static Set<String> spanningTreeNodes;
    private static List<Edge> spanningTreeEdges;

    public static void main(String[] args) {

        buildGraph();
        spanningTreeNodes = new HashSet<>();
        spanningTreeEdges = new ArrayList<>();

        for (String startNode : graph.keySet()) {
            if (!spanningTreeNodes.contains(startNode)) {
                primAlgorithm(startNode);
            }
        }

        int forestSum = spanningTreeEdges.stream().mapToInt(edge -> edge.getWeight()).sum();
        System.out.println("Minimum spanning forest weight: " + forestSum);
        for (Edge edge : spanningTreeEdges) {
            System.out.println(edge);
        }
    }

    private static void primAlgorithm(String startNode) {
        spanningTreeNodes.add(startNode);
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        for (Edge edge : graph.get(startNode)) {
            priorityQueue.add(edge);
        }

        while (priorityQueue.size() > 0) {
            Edge smallestEdge = priorityQueue.extractMin();

            /**
             * Same as ->
             * (spanningTreeNodes.contains(smallestEdge.getStartNode() &&
             * !spanningTreeNodes.contains(smallestEdge.getEndNode()) ||
             * (spanningTreeNodes.contains(smallestEdge.getEndNode() &&
             * !spanningTreeNodes.contains(smallestEdge.getStartNode())
             */
            if (spanningTreeNodes.contains(smallestEdge.getStartNode()) ^
                    spanningTreeNodes.contains(smallestEdge.getEndNode())) {
                String nonTreeNode = spanningTreeNodes.contains(smallestEdge.getStartNode()) ?
                        smallestEdge.getEndNode() :
                        smallestEdge.getStartNode();

                spanningTreeNodes.add(nonTreeNode);
                spanningTreeEdges.add(smallestEdge);

                for (Edge newEdge : graph.get(nonTreeNode)) {
                    if (newEdge != smallestEdge) {
                        priorityQueue.add(newEdge);
                    }
                }
            }
        }
    }

    private static void buildGraph() {
        graph = new HashMap<>();
        for (Edge edge : edges) {
            String startNode = edge.getStartNode();
            String endNode = edge.getEndNode();

            if (!graph.containsKey(startNode)) {
                graph.put(startNode, new ArrayList<>());
            }
            graph.get(startNode).add(edge);
            if (!graph.containsKey(endNode)) {
                graph.put(endNode, new ArrayList<>());
            }
            graph.get(endNode).add(edge);
        }
    }
}
