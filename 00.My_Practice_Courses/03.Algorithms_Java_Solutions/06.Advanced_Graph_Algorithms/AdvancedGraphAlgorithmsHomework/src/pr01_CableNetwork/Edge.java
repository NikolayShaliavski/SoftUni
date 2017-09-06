package pr01_CableNetwork;

public class Edge implements Comparable<Edge> {

    private Integer sourceNode;

    private Integer destinationNode;

    private Integer weight;

    public Edge(Integer sourceNode, Integer destinationNode, Integer weight) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.weight = weight;
    }

    public Integer getSourceNode() {
        return this.sourceNode;
    }

    public Integer getDestinationNode() {
        return this.destinationNode;
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
        return String.format("{%d, %d} -> %d",
                this.getSourceNode(),
                this.getDestinationNode(),
                this.getWeight());
    }
}
