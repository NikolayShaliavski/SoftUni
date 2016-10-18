package problem_01.vehicles;

import problem_01.IOpackage.Writer;

import java.text.DecimalFormat;

public class Car extends Vehicle {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.######");
    private static final Double CAR_CONSUMPTION_ADDITION = 0.9;

    public Car(String name,
               Double fuel,
               Double consumptionPerKilometer,
               Double tankCapacity) {
        super(name, fuel, consumptionPerKilometer, tankCapacity);
    }

    @Override
    public void drive(Double kilometers) {
        if (this.getFuel() / (this.getConsumption() + CAR_CONSUMPTION_ADDITION) < kilometers) {
            Writer.write(String.format("%s needs refueling", this.getName()));
        } else {
            Writer.write(String.format("%s travelled %s km", this.getName(), DECIMAL_FORMAT.format(kilometers)));
            this.setFuel(this.getFuel() - ((this.getConsumption() + CAR_CONSUMPTION_ADDITION) * kilometers));
        }
    }

    @Override
    public void refuel(Double litres) {
        if ((this.getFuel() + litres) > this.getTankCapacity()) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        this.setFuel(this.getFuel() + litres);
    }
}
