package problem_02.animals;

import problem_01.IOpackage.Writer;
import problem_02.foods.Food;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.######");

    private String breed;

    public Cat(String type, String name, Double weight, String region, String breed) {
        super(type, name, weight, region);
        this.setBreed(breed);
    }

    private String getBreed() {
        return this.breed;
    }

    private void setBreed(String breed) {
        if (breed == null || breed.length() == 0) {
            throw new IllegalArgumentException("Invalid breed.");
        }
        this.breed = breed;
    }

    @Override
    public void eat(Food food) {
        this.setFoodEaten(this.getFoodEaten() + food.getFoodQuantity());
    }

    @Override
    public void makeSound() {
        Writer.write("Meowwww");
    }

    @Override
    public String toString() {
        String formatWeight = DECIMAL_FORMAT.format(this.getWeight());
        return String.format("%s[%s, %s, %s, %s, %d]",
                this.getType(),
                this.getName(),
                this.getBreed(),
                formatWeight,
                this.getRegion(),
                this.getFoodEaten());
    }
}
