package problem_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P09_PizzaTime {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split("[\\s]+");
        TreeMap<Integer, LinkedHashSet<String>> collectedPizza = getAllPizza(line);
        for (Map.Entry pizza : collectedPizza.entrySet()) {
            System.out.printf("%d - %s%n",
                    pizza.getKey(),
                    pizza.getValue().toString().replaceAll("[\\[\\]]", ""));
        }
    }

    public static TreeMap<Integer, LinkedHashSet<String>> getAllPizza(String... pizzas) {
        List<Pizza> pizzaInstances = new ArrayList<>();
        Pattern patternGroup = Pattern.compile("([0-9]+)");
        Pattern patternName = Pattern.compile("([A-Z][A-Za-z]+)");

        for (String pizza : pizzas) {
            Matcher matcherGroup = patternGroup.matcher(pizza);
            Matcher matcherName = patternName.matcher(pizza);

            if (matcherGroup.find() && matcherName.find()) {
                int group = Integer.valueOf(matcherGroup.group(1));
                String name =  matcherName.group(1);

                Pizza currentPizza = new Pizza(name, group);
                pizzaInstances.add(currentPizza);
            }
        }
        TreeMap<Integer, LinkedHashSet<String>> collectedPizza = new TreeMap<>();
        for (Pizza pizza : pizzaInstances) {
            if (!collectedPizza.containsKey(pizza.getGroup())) {
                collectedPizza.put(pizza.getGroup(), new LinkedHashSet<>());
            }
            collectedPizza.get(pizza.getGroup()).add(pizza.getName());
        }
        return collectedPizza;
    }
}
class Pizza{
    private String name;
    private int group;

    public Pizza(String name, int group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return this.name;
    }

    public int getGroup() {
        return this.group;
    }
}