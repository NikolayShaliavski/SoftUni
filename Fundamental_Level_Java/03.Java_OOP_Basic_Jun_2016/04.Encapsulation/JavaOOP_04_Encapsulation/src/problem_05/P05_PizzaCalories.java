package problem_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P05_PizzaCalories {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        Pizza pizza = null;
        Dough dough = null;
        Topping topping = null;

        while (!line.equals("END")) {

            String[] tokens = line.split("[\\s]+");

            if (tokens[0].equals("Pizza")) {
                String pizzaName = tokens[1];
                int pizzaToppings = Integer.valueOf(tokens[2]);
                try {
                    pizza = new Pizza(pizzaName, pizzaToppings);
                } catch (IllegalArgumentException iaex) {
                    System.out.println(iaex.getMessage());
                    return;
                }
            } else if (tokens[0].equals("Dough")) {
                String typeDough = tokens[1];
                String backingDough = tokens[2];
                int weightDough = Integer.valueOf(tokens[3]);

                try {
                    dough = new Dough(typeDough, backingDough, weightDough);
                    if (pizza != null) {
                        pizza.setDough(dough);
                    }
                } catch (IllegalArgumentException iaex) {
                    System.out.println(iaex.getMessage());
                    return;
                }
            } else if (tokens[0].equals("Topping")) {
                String[] toppingTokens = line.split("[\\s]+");
                String typeTopping = toppingTokens[1];
                int weightTopping = Integer.valueOf(toppingTokens[2]);

                try {
                    topping = new Topping(typeTopping, weightTopping);
                    if (pizza != null) {
                        pizza.addToppings(topping);
                    }
                } catch (IllegalArgumentException iaex) {
                    System.out.println(iaex.getMessage());
                    return;
                }
            }
            line = reader.readLine();
        }
        if (pizza == null) {
            if (dough != null) {
                System.out.printf("%.2f%n", dough.getTotalCalories());
            }
            if (topping != null) {
                System.out.printf("%.2f%n", topping.getTotalCalories());
            }
        } else {
            System.out.printf("%s - %.2f Calories.%n", pizza.getName(), pizza.getTotalCalories());
        }
    }
}

class Pizza {

    private String name;
    private Dough dough;
    private List<Topping> toppingList;
    private int numberOfToppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setNumberOfToppings(numberOfToppings);
        this.toppingList = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    private void setNumberOfToppings(int numberOfToppings) {
        if (numberOfToppings < 0 || numberOfToppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in the range [0..10].");
        }
        this.numberOfToppings = numberOfToppings;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void addToppings(Topping topping) {
        this.toppingList.add(topping);
    }

    public String getName() {
        return this.name;
    }

    public double getTotalCalories() {
        return this.calculateCalories();
    }

    public int getNumberOfToppings() {
        return this.numberOfToppings;
    }

    private double calculateCalories() {
        double totalCalories = 0.0;
        totalCalories += this.dough.getTotalCalories();
        for (Topping topping : toppingList) {
            totalCalories += topping.getTotalCalories();
        }
        return totalCalories;
    }
}

class Dough {

    private static final int BASE_CALORIES_PER_GRAM = 2;
    private static HashMap<String, Double> modifiers = setModifiers();

    private String type;
    private String backingTechnique;
    private int weight;
    private double typeModifier;
    private double techniqueModifier;
    private double totalCalories;

    public Dough(String type, String backingTechnique, int weight) {
        this.setType(type);
        this.setBackingTechnique(backingTechnique);
        this.setWeight(weight);
        this.setTypeModifier();
        this.setTechniqueModifier();
        this.setTotalCalories();
    }

    private void setType(String type) {
        if (!modifiers.containsKey(type.toLowerCase())) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.type = type;
    }

    private void setBackingTechnique(String backingTechnique) {
        if (!modifiers.containsKey(backingTechnique.toLowerCase())) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.backingTechnique = backingTechnique;
    }

    private void setWeight(int weight) {
        if (weight <= 0 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double getTotalCalories() {
        return this.totalCalories;
    }

    private static HashMap setModifiers() {
        modifiers = new HashMap<>();
        modifiers.put("white", 1.5);
        modifiers.put("wholegrain", 1.0);
        modifiers.put("crispy", 0.9);
        modifiers.put("chewy", 1.1);
        modifiers.put("homemade", 1.0);

        return modifiers;
    }

    private void setTypeModifier() {
        this.typeModifier = modifiers.get(this.type.toLowerCase());
    }

    private void setTechniqueModifier() {
        this.techniqueModifier = modifiers.get(this.backingTechnique.toLowerCase());
    }

    private void setTotalCalories() {
        this.totalCalories = BASE_CALORIES_PER_GRAM * this.weight * this.typeModifier * this.techniqueModifier;
    }
}

class Topping {

    private static final int BASE_CALORIES_PER_GRAM = 2;
    private static HashMap<String, Double> modifiers = setModifiers();

    private String type;
    private int weight;
    private double modifier;
    private double totalCalories;

    public Topping(String type, int weight) {
        this.setType(type);
        this.setWeight(weight);
        this.setModifier();
        this.setTotalCalories();
    }

    private void setType(String type) {
        if (!modifiers.containsKey(type.toLowerCase())) {
            throw new IllegalArgumentException("Cannot place " + type + " on top of your pizza.");
        }
        this.type = type;
    }

    private void setWeight(int weight) {
        if (weight <= 0 || weight > 50) {
            throw new IllegalArgumentException(type + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    public double getTotalCalories() {
        return this.totalCalories;
    }

    private static HashMap setModifiers() {
        modifiers = new HashMap<>();
        modifiers.put("meat", 1.2);
        modifiers.put("veggies", 0.8);
        modifiers.put("cheese", 1.1);
        modifiers.put("sauce", 0.9);

        return modifiers;
    }

    private void setModifier() {
        this.modifier = modifiers.get(this.type.toLowerCase());
    }

    private void setTotalCalories() {
        this.totalCalories = this.weight * BASE_CALORIES_PER_GRAM * this.modifier;
    }
}