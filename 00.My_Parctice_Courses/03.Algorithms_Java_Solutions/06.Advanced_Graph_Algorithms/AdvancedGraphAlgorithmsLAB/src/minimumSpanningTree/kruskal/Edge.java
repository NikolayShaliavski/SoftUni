package minimumSpanningTree.kruskal;

public class Edge implements Comparable<Edge> {

    private Integer startNode;

    private Integer endNode;

    private Integer weight;

    public Edge(Integer startNode, Integer endNode, Integer weight) {
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

    public Integer getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.getWeight().compareTo(other.getWeight());
    }

    @Override
    public String toString() {
        return String.format("(%d %d) -> %d",
                this.getStartNode(),
                this.getEndNode(),
                this.getWeight());
    }
}
