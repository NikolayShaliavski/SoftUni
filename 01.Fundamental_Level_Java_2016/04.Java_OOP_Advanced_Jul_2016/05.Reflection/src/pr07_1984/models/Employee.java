package pr07_1984.models;

public class Employee extends AbstractEntity {

    private String name;
    private int income;

    public Employee(String id,
                    String name,
                    int income) {
        super(id);
        this.name = name;
        this.income = income;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
