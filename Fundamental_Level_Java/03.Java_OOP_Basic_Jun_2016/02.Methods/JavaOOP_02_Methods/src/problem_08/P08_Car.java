package problem_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P08_Car {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] initCar = reader.readLine().split("[\\s]+");
        Car car = new Car(Integer.valueOf(initCar[0]), Integer.valueOf(initCar[1]), Integer.valueOf(initCar[2]));

        String line = reader.readLine();
        while (!line.equals("END")) {
            String[] tokens = line.split("[\\s]+");
            if (tokens.length == 2) {
                if (tokens[0].equals("Travel")) {
                    int distance = Integer.valueOf(tokens[1]);
                    car.travelDistance(distance);
                } else if (tokens[0].equals("Refuel")) {
                    int liters = Integer.valueOf(tokens[1]);
                    car.refuel(liters);
                }
            } else if (line.equals("Distance")) {
                System.out.println(car.distance());
            } else if (line.equals("Time")) {
                System.out.println(car.time());
            } else if (line.equals("Fuel")) {
                System.out.println(car.fuelLeft());
            }
            line = reader.readLine();
        }
    }
}

class Car {

    private double speed;
    private double fuel;
    private double fuelEconomy;
    private double distance;
    private double time;

    public Car(double speed, double fuel, double fuelEconomy) {
        this.speed = speed;
        this.fuel = fuel;
        this.fuelEconomy = fuelEconomy;
        this.distance = 0;
        this.time = 0;
    }

    private double getSpeed() {
        return speed;
    }

    private double getFuel() {
        return fuel;
    }

    private double getFuelEconomy() {
        return fuelEconomy;
    }

    private double getDistance() {
        return distance;
    }

    private double getTime() {
        return time;
    }

    public void travelDistance(double distance) {
        double possibleDistance = getPossibleDistance();
        if (this.getFuel() > 0 && possibleDistance < distance) {
            this.fuel = 0;
            this.time += possibleDistance / this.getSpeed();
            this.distance += possibleDistance;
        } else {
            this.distance += distance;
            this.fuel -= (this.getFuelEconomy() * distance) / 100;
            this.time += distance / this.getSpeed();
        }
    }

    private double getPossibleDistance() {
        return (this.getFuel() / this.getFuelEconomy()) * 100;
    }

    public void refuel(double liters) {
        this.fuel += liters;
    }

    public String distance() {
        return String.format("Total distance: %.1f kilometers", this.getDistance());
    }

    public String time() {
        int totalHours = (int) this.getTime();
        double minutes = (this.getTime() - totalHours) * 60;
        return String.format("Total time: %d hours and %.0f minutes", totalHours, minutes);
    }

    public String fuelLeft() {
        return String.format("Fuel left: %.1f liters", (double) this.getFuel());
    }
}