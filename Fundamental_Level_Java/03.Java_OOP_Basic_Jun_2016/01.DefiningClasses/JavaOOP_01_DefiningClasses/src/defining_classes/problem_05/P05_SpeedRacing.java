package defining_classes.problem_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P05_SpeedRacing {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfCars = Integer.valueOf(reader.readLine());
        Car[] cars = new Car[numberOfCars];

        for (int i = 0; i < numberOfCars; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String model = tokens[0];
            double fuel = Double.valueOf(tokens[1]);
            double fuelPerKm = Double.valueOf(tokens[2]);
            Car car = new Car(model, fuel, fuelPerKm);
            cars[i] = car;
        }
        String command = reader.readLine();
        while (!command.equals("End")) {
            String[] tokens = command.split("[\\s]+");
            String model = tokens[1];
            int distance = Integer.valueOf(tokens[2]);
            for (int i = 0; i < cars.length; i++) {
                String carModel = cars[i].getModel();
                if (carModel.equals(model)) {
                    Car carToDrive = cars[i];
                    if (carToDrive.canDrive(distance)) {
                        carToDrive.setDistance(carToDrive.getDistance() + distance);
                        carToDrive.setFuel(carToDrive.getFuel() - (distance * carToDrive.getFuelPerKm()));
                        cars[i] = carToDrive;
                    } else {
                        System.out.println("Insufficient fuel for the drive");
                    }
                }
            }
            command = reader.readLine();
        }
        for (Car car : cars) {
            System.out.printf("%s %.2f %d%n",
                    car.getModel(),
                    car.getFuel(),
                    car.getDistance());
        }
    }
}

class Car {
    String model;
    double fuel;
    double fuelPerKm;
    int distance;

    public Car(String model, double fuel, double fuelPerKm) {
        this.model = model;
        this.fuel = fuel;
        this.fuelPerKm = fuelPerKm;

        this.distance = 0;
    }

    public boolean canDrive(int distance) {
        if ((this.fuel / this.fuelPerKm) >= distance) {
            return true;
        }
        return false;
    }

    public String getModel() {
        return model;
    }

    public double getFuel() {
        return fuel;
    }

    public double getFuelPerKm() {
        return fuelPerKm;
    }

    public int getDistance() {
        return distance;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}