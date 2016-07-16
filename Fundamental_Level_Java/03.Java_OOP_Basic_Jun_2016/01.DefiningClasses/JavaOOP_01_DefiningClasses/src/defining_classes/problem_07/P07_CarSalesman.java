package defining_classes.problem_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P07_CarSalesman {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfEngines = Integer.valueOf(reader.readLine());
        Engine[] engines = new Engine[numberOfEngines];

        for (int i = 0; i < numberOfEngines; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String engineModel = tokens[0];
            int enginePower = Integer.valueOf(tokens[1]);

            if (tokens.length == 4) {
                String engineDisplacement = tokens[2];
                String engineEfficiency = tokens[3];
                Engine engine = new Engine(engineModel, enginePower, engineDisplacement, engineEfficiency);
                engines[i] = engine;
            } else if (tokens.length == 3) {
                if (isDigit(tokens[2])) {
                    int engineDisplacement = Integer.valueOf(tokens[2]);
                    Engine engine = new Engine(engineModel, enginePower, engineDisplacement);
                    engines[i] = engine;
                } else {
                    String engineEfficiency = tokens[2];
                    Engine engine = new Engine(engineModel, enginePower, engineEfficiency);
                    engines[i] = engine;
                }
            } else {
                Engine engine = new Engine(engineModel, enginePower);
                engines[i] = engine;
            }

        }
        int numberOfCars = Integer.valueOf(reader.readLine());
        Car[] cars = new Car[numberOfCars];

        for (int i = 0; i < numberOfCars; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String model = tokens[0];
            String engineModel = tokens[1];
            Engine engine = null;
            for (Engine currentEngine : engines) {
                if (currentEngine.getModel().equals(engineModel)) {
                    engine = currentEngine;
                }
            }
            if (tokens.length == 4) {
                String weight = tokens[2];
                String color = tokens[3];
                Car car = new Car(model, engine, weight, color);
                cars[i] = car;
            } else if (tokens.length == 3) {
                if (isDigit(tokens[2])) {
                    int weight = Integer.valueOf(tokens[2]);
                    Car car = new Car(model, engine, weight);
                    cars[i] = car;
                } else {
                    String color = tokens[2];
                    Car car = new Car(model, engine, color);
                    cars[i] = car;
                }
            } else {
                Car car = new Car(model, engine);
                cars[i] = car;
            }
        }

        for (Car car : cars) {
            System.out.printf("%s:%n", car.getModel());
            System.out.printf("  %s:%n", car.getEngine().getModel());
            System.out.printf("    Power: %d%n", car.getEngine().getPower());
            System.out.printf("    Displacement: %s%n", car.getEngine().getDisplacement());
            System.out.printf("    Efficiency: %s%n", car.getEngine().getEfficiency());
            System.out.printf("  Weight: %s%n", car.getWeight());
            System.out.printf("  Color: %s%n", car.getColor());

            //System.out.println(car.toString());
        }
    }

    public static boolean isDigit(String digit) {
        try {
            int result = Integer.parseInt(digit);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}

class Car {
    static final String DEFAULT_PROPERTY = "n/a";

    String model;
    Engine engine;
    String weight;
    String color;

    public Car(String model, Engine engine, String weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public Car(String model, Engine engine, Integer weight) {
        this(model, engine, weight.toString(), DEFAULT_PROPERTY);
    }

    public Car(String model, Engine engine, String color) {
        this(model, engine, DEFAULT_PROPERTY, color);
    }

    public Car(String model, Engine engine) {
        this(model, engine, DEFAULT_PROPERTY, DEFAULT_PROPERTY);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.model + " ").append(this.engine.getModel() + " ")
                .append(this.engine.getPower() + " ")
                .append(this.engine.getDisplacement() + " ")
                .append(this.engine.getEfficiency() + " ")
                .append(this.weight + " ")
                .append(this.color);
        return builder.toString();
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }
}

class Engine {
    static final String DEFAULT_PROPERTY = "n/a";

    String model;
    int power;
    String displacement;
    String efficiency;

    public Engine(String model, int power, String displacement, String efficiency) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    public Engine(String model, int power, Integer displacement) {
        this(model, power, displacement.toString(), DEFAULT_PROPERTY);
    }

    public Engine(String model, int power, String efficiency) {
        this(model, power, DEFAULT_PROPERTY, efficiency);
    }

    public Engine(String model, int power) {
        this(model, power, DEFAULT_PROPERTY, DEFAULT_PROPERTY);
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public String getDisplacement() {
        return displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }
}