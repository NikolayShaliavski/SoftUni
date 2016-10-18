package defining_classes.problem_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P06_RawData {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        Car[] cars = new Car[lines];
        
        for (int i = 0; i < lines; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String model = tokens[0];
            int engineSpeed = Integer.valueOf(tokens[1]);
            int enginePower = Integer.valueOf(tokens[2]);
            Engine engine = new Engine(engineSpeed, enginePower);
            int cargoWeight = Integer.valueOf(tokens[3]);
            String cargoType = tokens[4];
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Tyre[] tyres = new Tyre[4];
            for (int j = 5; j < tokens.length - 1; j += 2) {
                double tyrePressure = Double.valueOf(tokens[j]);
                int tyreAge = Integer.valueOf(tokens[j + 1]);
                Tyre tyre = new Tyre(tyrePressure, tyreAge);
                tyres[(j - 5) / 2] = tyre;
            }
            Car car = new Car(model, engine, cargo, tyres);
            cars[i] = car;
        }
        String command = reader.readLine();
        for (Car car : cars) {
            if (command.equals("fragile")) {
                if (car.getCargo().getType().equals(command)) {
                    for (Tyre tyre : car.getTyres()) {
                        if (tyre.getPressure() < 1) {
                            System.out.println(car.getModel());
                            break;
                        }
                    }
                }
            } else {
                if (car.getCargo().getType().equals(command)) {
                    if (car.getEngine().getPower() > 250) {
                        System.out.println(car.getModel());
                    }
                }
            }
        }
    }
}

class Car {
    String model;
    Engine engine;
    Cargo cargo;
    Tyre[] tyres;

    public Car(String model, Engine engine, Cargo cargo, Tyre[] tyres) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tyres = tyres;
    }

    public String getModel() {
        return model;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Tyre[] getTyres() {
        return tyres;
    }

    public Engine getEngine() {
        return engine;
    }
}

class Engine {
    int speed;
    int power;

    public Engine(int speed, int power) {
        this.speed = speed;
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}

class Cargo {
    int weight;
    String type;

    public Cargo(int weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Tyre {
    double pressure;
    int age;

    public Tyre(double pressure, int age) {
        this.pressure = pressure;
        this.age = age;
    }

    public double getPressure() {
        return pressure;
    }
}