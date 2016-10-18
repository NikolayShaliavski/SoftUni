package problem_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P04_ShoppingSpree {

    static List<Person> people = new LinkedList<>();
    static List<Product> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] persons = reader.readLine().split(";");
        for (String personInfo : persons) {
            String[] properties = personInfo.split("=");
            String name = properties[0];
            int money = Integer.valueOf(properties[1]);

            try {
                Person person = new Person(name, money);
                people.add(person);
            } catch (IllegalArgumentException iaex) {
                System.out.println(iaex.getMessage());
                return;
            }
        }

        String[] productsLine = reader.readLine().split(";");
        for (String currentProduct : productsLine) {
            String[] productInfo = currentProduct.split("=");
            String name = productInfo[0];
            int price = Integer.valueOf(productInfo[1]);

            try {
                Product product = new Product(name, price);
                products.add(product);
            } catch (IllegalArgumentException iaex) {
                System.out.println(iaex.getMessage());
                return;
            }
        }
        String line = reader.readLine();
        while (!line.equals("END")) {
            String[] tokens = line.split("[\\s]+");
            String name = tokens[0];
            String productName = tokens[1];

            Person person = getPerson(name);
            Product product = getProduct(productName);
            person.tryToBuy(product);

            line = reader.readLine();
        }
        for (Person person : people) {
            System.out.println(person.doesPersonBuySomething());
        }
    }

    private static Product getProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    private static Person getPerson(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
}
class Person {

    private String name;
    private int money;
    private List<String> bag;

    public Person(String name, int money) {
        this.setName(name);
        this.setMoney(money);
        this.bag = new LinkedList<>();
    }

    private void setName(String name) {
        if (name.isEmpty() || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    private int getMoney() {
        return this.money;
    }

    private List<String> getBag() {
        return this.bag;
    }

    public void tryToBuy(Product product) {
        if (this.getMoney() - product.getPrice() < 0) {
            System.out.printf("%s can't afford %s%n",
                    this.getName(),
                    product.getName());
        } else {
            System.out.printf("%s bought %s%n",
                    this.getName(),
                    product.getName());
            this.money -= product.getPrice();
            this.bag.add(product.getName());
        }
    }

    public String doesPersonBuySomething() {
        if (this.bag.size() == 0) {
            return String.format("%s - Nothing bought", this.getName());
        }
        return String.format("%s - %s",
                this.getName(),
                this.getBag().toString().replaceAll("[\\[\\]]", ""));
    }
}
class Product {

    private String name;
    private int price;

    public Product(String name, int price) {
        this.setName(name);
        this.setPrice(price);
    }

    private void setName(String name) {
        if (name.isEmpty() || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}