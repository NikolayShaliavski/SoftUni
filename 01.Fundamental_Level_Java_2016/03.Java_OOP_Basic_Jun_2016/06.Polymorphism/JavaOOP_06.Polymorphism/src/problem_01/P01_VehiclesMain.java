package problem_01;


import problem_01.IOpackage.Reader;
import problem_01.IOpackage.Writer;
import problem_01.vehicles.Bus;
import problem_01.vehicles.Car;
import problem_01.vehicles.Truck;

import java.io.IOException;

public class P01_VehiclesMain {

    public static void main(String[] args) throws IOException {

        String[] carParams = Reader.read().split("[\\s]+");
        String[] truckParams = Reader.read().split("[\\s]+");
        String[] busParams = Reader.read().split("[\\s]+");

        String carName = carParams[0];
        Double carFuel = Double.valueOf(carParams[1]);
        Double carConsumption = Double.valueOf(carParams[2]);
        Double carTankCapacity = Double.valueOf(carParams[3]);

        String truckName = truckParams[0];
        Double truckFuel = Double.valueOf(truckParams[1]);
        Double truckConsumption = Double.valueOf(truckParams[2]);
        Double truckTankCapacity = Double.valueOf(truckParams[3]);

        String busName = busParams[0];
        Double busFuel = Double.valueOf(busParams[1]);
        Double busConsumption = Double.valueOf(busParams[2]);
        Double busTankCapacity = Double.valueOf(busParams[3]);

        Car car = null;
        Truck truck = null;
        Bus bus = null;
        try {
            car = new Car(carName, carFuel, carConsumption, carTankCapacity);
            truck = new Truck(truckName, truckFuel, truckConsumption, truckTankCapacity);
            bus = new Bus(busName, busFuel, busConsumption, busTankCapacity);
        } catch (IllegalArgumentException iaex) {
            Writer.write(iaex.getMessage());
            return;
        }

        int numberOfCommands = Integer.valueOf(Reader.read());

        for (int i = 0; i < numberOfCommands; i++) {
            String[] commands = Reader.read().split("[\\s]+");
            String command = commands[0];
            String vehicle = commands[1];
            Double value = Double.valueOf(commands[2]);

            try {
                if (command.equals("Drive")) {
                    if (vehicle.equals("Car")) {
                        car.drive(value);
                    } else if (vehicle.equals("Truck")) {
                        truck.drive(value);
                    } else if (vehicle.equals("Bus")) {
                        bus.drive(value);
                    }
                } else if (command.equals("Refuel")) {
                    if (vehicle.equals("Car")) {
                        car.refuel(value);
                    } else if (vehicle.equals("Truck")) {
                        truck.refuel(value);
                    } else if (vehicle.equals("Bus")) {
                        bus.refuel(value);
                    }
                } else if (command.equals("DriveEmpty")) {
                    bus.driveEmpty(value);
                }
            } catch (IllegalArgumentException iaex) {
                Writer.write(iaex.getMessage());
            }
        }
        car.print();
        truck.print();
        bus.print();
    }
}
