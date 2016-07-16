package problem_02.animals;

import problem_01.IOpackage.Writer;
import problem_02.foods.Food;

public class Mouse extends Mammal {

    public Mouse(String type, String name, Double weight, String region) {
        super(type, name, weight, region);
    }

    @Override
    public void eat(Food food) {
        if (food.getClass().getSimpleName().equals("Meat")) {
            Writer.write(String.format("%ss are not eating that type of food!", this.getType()));
            return;
        }
        this.setFoodEaten(this.getFoodEaten() + food.getFoodQuantity());
    }

    @Override
    public void makeSound() {
        Writer.write("SQUEEEAAAK!");
    }
}
