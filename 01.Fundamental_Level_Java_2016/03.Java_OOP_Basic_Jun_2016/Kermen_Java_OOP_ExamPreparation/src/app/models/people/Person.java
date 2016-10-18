package app.models.people;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class Person {

    private Double income;

    public Person(Double income) {
        this.setIncome(income);
    }

    public Double getIncome() {
        return this.income;
    }
    private void setIncome(Double income) {
        if (income < 0) {
            throw new IllegalArgumentException("Income cannot be less than zero!");
        }
        this.income = income;
    }
}
