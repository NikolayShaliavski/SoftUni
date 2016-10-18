package problem_01.vehicles;

import problem_01.IOpackage.Writer;

public abstract class Vehicle {

    private String name;
    private Double fuel;
    private Double consumptionPerKilometer;
    private Double tankCapacity;

    protected Vehicle(String name,
                      Double fuel,
                      Double consumptionPerKilometer,
                      Double tankCapacity) {
        this.setName(name);
        this.setFuel(fuel);
        this.setConsumption(consumptionPerKilometer);
        this.setTankCapacity(tankCapacity);
    }

    protected String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Invalid vehicle name.");
        }
        this.name = name;
    }

    protected Double getFuel() {
        return this.fuel;
    }

    protected void setFuel(Double fuel) {
        if (fuel < 0) {
            throw new IllegalArgumentException("Fuel cannot be less than zero.");
        }
        this.fuel = fuel;
    }

    protected Double getConsumption() {
        return this.consumptionPerKilometer;
    }

    private void setConsumption(Double consumption) {
        if (consumption < 0) {
            throw new IllegalArgumentException("Consumption per kilometer cannot be less than zero.");
        }
        this.consumptionPerKilometer = consumption;
    }

    protected Double getTankCapacity() {
        return this.tankCapacity;
    }

    private void setTankCapacity(Double tankCapacity) {
        if (tankCapacity <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        this.tankCapacity = tankCapacity;
    }
    protected abstract void drive(Double kilometers);

    protected abstract void refuel(Double litres);

    public void print() {
        Writer.write(String.format("%s: %.2f", this.getName(), this.getFuel()));
    }
}
