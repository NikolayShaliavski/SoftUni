package problem_02.animals;

import problem_02.contracts.Eatable;
import problem_02.contracts.Soundable;

public abstract class Animal implements Eatable, Soundable {
    private static final Integer HUNGRY = 0;

    private String type;
    private String name;
    private Double weight;
    private Integer foodEaten;

    protected Animal(String type,
                     String name,
                     Double weight) {
        this.setType(type);
        this.setName(name);
        this.setWeight(weight);
        this.setFoodEaten(HUNGRY);
    }

    protected String getType() {
        return this.type;
    }

    private void setType(String type) {
        if (type == null || type.length() == 0) {
            throw new IllegalArgumentException("Invalid animal type.");
        }
        this.type = type;
    }

    protected String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Invalid animal name.");
        }
        this.name = name;
    }

    protected Double getWeight() {
        return this.weight;
    }

    private void setWeight(Double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid animal weight.");
        }
        this.weight = weight;
    }

    protected Integer getFoodEaten() {
        return this.foodEaten;
    }

    protected void setFoodEaten(Integer foodEaten) {
        if (foodEaten < 0) {
            throw new IllegalArgumentException("Invalid food quantity.");
        }
        this.foodEaten = foodEaten;
    }
}
