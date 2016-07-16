package problem_02;

import problem_01.IOpackage.Reader;
import problem_01.IOpackage.Writer;
import problem_02.animals.*;
import problem_02.foods.Food;
import problem_02.foods.Meat;
import problem_02.foods.Vegetable;

import java.io.IOException;

public class P02_WildFarmMain {

    public static void main(String[] args) throws IOException {

        String line = Reader.read();

        while (!line.equals("End")) {
            String[] animalParams = line.split("[\\s]+");
            String[] foodParams = Reader.read().split("[\\s]+");

            String animalType = animalParams[0];
            String animalName = animalParams[1];
            Double animalWeight = Double.valueOf(animalParams[2]);
            String region = animalParams[3];

            String foodName = foodParams[0];
            Integer foodQuantity = Integer.valueOf(foodParams[1]);

            try {
                Food food = null;
                if (foodName.equals("Meat")) {
                    food = new Meat(foodQuantity);
                } else if (foodName.equals("Vegetable")) {
                    food = new Vegetable(foodQuantity);
                }

                Animal animal = null;

                switch (animalType) {
                    case "Cat":
                        String breed = animalParams[4];
                        animal = new Cat(animalType, animalName, animalWeight, region, breed);
                        break;
                    case "Mouse":
                        animal = new Mouse(animalType, animalName, animalWeight, region);
                        break;
                    case "Tiger":
                        animal = new Tiger(animalType, animalName, animalWeight, region);
                        break;
                    case "Zebra":
                        animal = new Zebra(animalType, animalName, animalWeight, region);
                        break;
                }

                if (animal != null) {
                    animal.makeSound();
                    animal.eat(food);
                    Writer.write(animal.toString());
                }

            } catch (IllegalArgumentException iaex) {
                Writer.write(iaex.getMessage());
            }
            line = Reader.read();
        }
    }
}
