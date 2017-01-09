package knapsackProblem;

public class Item {

    private int weight;

    private int price;

    public Item(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("Item: {weight - %d, price - %d}", this.weight, this.price);
    }
}
