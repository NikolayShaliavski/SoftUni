package pr07_1984.models;

public class Company extends AbstractEntity {

    private String name;
    private int turnover;
    private int revenue;

    public Company(String id,
                   String name,
                   int turnover,
                   int revenue) {
        super(id);
        this.name = name;
        this.turnover = turnover;
        this.revenue = revenue;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
