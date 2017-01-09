package dijkstra.dijkstraPriorityQueue;

public class Edge {

    private int destination;

    private double distance;

    public Edge(int destination, double distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public int getDestination() {
        return this.destination;
    }

    public double getDistance() {
        return this.distance;
    }
}
