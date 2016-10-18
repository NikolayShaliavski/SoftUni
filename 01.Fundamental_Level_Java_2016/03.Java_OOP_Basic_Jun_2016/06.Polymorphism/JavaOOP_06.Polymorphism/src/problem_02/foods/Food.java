package problem_02.foods;

public abstract class Food {

    private Integer foodQuantity;

    protected Food(Integer foodQuantity) {
        this.setFoodQuantity(foodQuantity);
    }

    public Integer getFoodQuantity() {
        return this.foodQuantity;
    }

    private void setFoodQuantity(Integer foodQuantity) {
        if (foodQuantity < 0) {
            throw new IllegalArgumentException("Food cannot be negative number.");
        }
        this.foodQuantity = foodQuantity;
    }
}
