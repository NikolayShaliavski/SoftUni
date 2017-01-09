package pr04_FloydWarshall;

public class Edge implements Comparable<Edge> {

    private Integer startNode;

    private Integer endNode;

    private Double weight;

    public Edge(Integer startNode, Integer endNode, Double weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public Integer getStartNode() {
        return this.startNode;
    }

    public Integer getEndNode() {
        return this.endNode;
    }

    public Double getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.getWeight().compareTo(other.getWeight());
    }

    @Override
    public String toString() {
        return String.format("(%d %d) -> %.0f",
                this.getStartNode(),
                this.getEndNode(),
                this.getWeight());
    }
}
