package pr03_Evacuation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Uses Dijkstra algorithm to calculate
 * best times from each exit to other nodes
 */
public class Evacuation {

    static Node[] nodes;
    static List<Edge>[] edges;
    static List<Node> exits;
    static Integer timeToEvacuate;

    public static void main(String[] args) throws IOException {
        readInput();

        for (Node exit : exits) {
            dijkstraFindDistances(exit);
        }
        List<Node> unsafeRooms = new ArrayList<>();
        for (Node node : nodes) {
            if (node.distanceFromStart > timeToEvacuate) {
                unsafeRooms.add(node);
            }
        }

        if (unsafeRooms.size() > 0) {
            System.out.println("Unsafe");
            StringBuilder sb = new StringBuilder();
            for (Node unsafeRoom : unsafeRooms) {
                Integer id = unsafeRoom.id;
                String time = convertTime(unsafeRoom.distanceFromStart);
                sb.append(String.format("%d (%s), ", id, time));
            }
            System.out.println(sb.substring(0, sb.length() - 2));
        } else {
            System.out.println("Safe");
            Node maxTimeRoom = getRoomMaxTimeToEvacuate();
            Integer id = maxTimeRoom.id;
            String time = convertTime(maxTimeRoom.distanceFromStart);
            System.out.printf("%d (%s)", id, time);
        }
    }

    private static Node getRoomMaxTimeToEvacuate() {
        Node room = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].distanceFromStart > room.distanceFromStart) {
                room = nodes[i];
            } else if (nodes[i].distanceFromStart == room.distanceFromStart) {
                if (nodes[i].id < room.id) {
                    room = nodes[i];
                }
            }
        }
        return room;
    }

    private static void dijkstraFindDistances(Node start) {
        start.distanceFromStart = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(start);

        while (priorityQueue.size() > 0) {
            Node currNode = priorityQueue.poll();

            List<Edge> children = edges[currNode.id];
            for (Edge edge : children) {
                Node child = nodes[edge.destination];

                Integer newDistance = currNode.distanceFromStart + edge.weight;
                if (newDistance < child.distanceFromStart) {
                    child.distanceFromStart = newDistance;
//                    if (!priorityQueue.contains(child)) {
//                        priorityQueue.add(child);
//                    } else {
//                        priorityQueue.remove(child);
//                        priorityQueue.add(child);
//                    }
                    priorityQueue.add(child);
                }
            }
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Integer rooms = Integer.valueOf(bf.readLine());
        nodes = new Node[rooms];
        edges = new List[rooms];
        for (int i = 0; i < rooms; i++) {
            nodes[i] = new Node(i);
            edges[i] = new ArrayList<>();
        }
        exits = new ArrayList<>();
        int[] exitRooms = Arrays.stream(bf.readLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < exitRooms.length; i++) {
            exits.add(nodes[exitRooms[i]]);
        }
        int connections = Integer.valueOf(bf.readLine());
        for (int i = 0; i < connections; i++) {
            String[] tokens = bf.readLine().split(" ");
            Integer source = Integer.valueOf(tokens[0]);
            Integer destination = Integer.valueOf(tokens[1]);
            Integer distance = parseTime(tokens[2]);

            edges[source].add(new Edge(destination, distance));
            edges[destination].add(new Edge(source, distance));
        }
        timeToEvacuate = parseTime(bf.readLine());
    }

    private static Integer parseTime(String token) {
        String[] timeTokens = token.split("[\\:]");
        int[] timeArr = new int[2];
        for (int i = 0; i < timeArr.length; i++) {
            String timeValue = timeTokens[i];
            if (timeValue.equals("00")) {
                timeArr[i] = 0;
            } else if (timeValue.startsWith("0")) {
                timeArr[i] = Integer.valueOf(timeValue.charAt(1) + "");
            } else {
                timeArr[i] = Integer.valueOf(timeValue);
            }
        }
        return timeArr[0] * 60 + timeArr[1];
    }

    private static String convertTime(int time) {
        if (time == Integer.MAX_VALUE) {
            return "unreachable";
        }
        StringBuilder sb = new StringBuilder();

        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time % 60;

        if (hours < 10) {
            sb.append("0").append(hours).append(":");
        } else {
            sb.append(hours).append(":");
        }
        if (minutes < 10) {
            sb.append("0").append(minutes).append(":");
        } else {
            sb.append(minutes).append(":");
        }
        if (seconds < 10) {
            sb.append("0").append(seconds);
        } else {
            sb.append(seconds);
        }
        return sb.toString();
    }

}

class Node implements Comparable<Node> {

    Integer id;
    Integer distanceFromStart;

    public Node(Integer id) {
        this.id = id;
        this.distanceFromStart = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Node other) {
        return this.distanceFromStart.compareTo(other.distanceFromStart);
    }
}

class Edge {

    Integer destination;
    Integer weight;

    public Edge(Integer destination, Integer weight) {
        this.destination = destination;
        this.weight = weight;
    }
}