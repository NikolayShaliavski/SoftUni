package pr05_BellmanFord;

public class Edge implements Comparable<Edge> {

    private Integer source;

    private Integer destination;

    private Double weight;

    public Edge(Integer source, Integer destination, Double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Integer getSource() {
        return this.source;
    }

    public Integer getDestination() {
        return this.destination;
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
                this.getSource(),
                this.getDestination(),
                this.getWeight());
    }
}
