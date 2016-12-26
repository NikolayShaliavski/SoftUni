package minimumSpanningTree.prim;

public class Edge implements Comparable<Edge> {

    private String startNode;

    private String endNode;

    private Integer weight;

    public Edge(String startNode, String endNode, Integer weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public String getStartNode() {
        return this.startNode;
    }

    public String getEndNode() {
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
        return String.format("(%s %s) -> %d",
                this.getStartNode(),
                this.getEndNode(),
                this.getWeight());
    }
}
