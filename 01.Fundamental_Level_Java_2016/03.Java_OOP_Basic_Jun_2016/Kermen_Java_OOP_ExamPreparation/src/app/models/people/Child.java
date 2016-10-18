package app.models.people;

import java.util.Arrays;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class Child {

    private Double expense;

    public Child(String childInfo) {
        this.setExpense(childInfo);
    }

    public Double getExpense() {
        return this.expense;
    }

    private void setExpense(String childInfo) {

        String[] allExpenses = childInfo.split("\\D+");
        this.expense = Arrays.stream(allExpenses).
                mapToDouble(Double::parseDouble).
                sum();
    }
}
