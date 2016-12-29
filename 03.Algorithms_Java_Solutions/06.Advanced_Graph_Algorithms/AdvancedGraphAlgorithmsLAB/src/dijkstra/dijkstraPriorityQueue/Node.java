package dijkstra.dijkstraPriorityQueue;

/**
 * Each node in the graph has id
 * and knows it's distance from start(source)
 */
public class Node implements Comparable<Node> {

    private int id;

    private Double distanceFromStart;

    public Node(int id) {
        this.id = id;
        this.setDistanceFromStart(Double.MAX_VALUE);
    }

    public int getId() {
        return this.id;
    }

    public Double getDistanceFromStart() {
        return this.distanceFromStart;
    }

    public void setDistanceFromStart(Double distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    @Override
    public int compareTo(Node other) {
        return this.getDistanceFromStart().compareTo(other.getDistanceFromStart());
    }
}
