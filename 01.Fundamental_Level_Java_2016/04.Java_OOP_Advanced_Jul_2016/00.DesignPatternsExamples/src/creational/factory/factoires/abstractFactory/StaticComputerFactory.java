package creational.factory.factoires.abstractFactory;

import creational.factory.entities.Computer;

public class StaticComputerFactory {

    public static Computer getComputer(ComputerAbstractFactory factory) {
        return factory.createComputer();
    }
}
