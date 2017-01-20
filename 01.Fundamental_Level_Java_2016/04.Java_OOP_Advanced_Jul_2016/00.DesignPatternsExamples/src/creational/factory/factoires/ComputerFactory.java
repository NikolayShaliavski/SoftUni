package creational.factory.factoires;

import creational.factory.entities.Computer;
import creational.factory.entities.PC;
import creational.factory.entities.Server;

public class ComputerFactory {

    public static Computer getComputer(String type, String ram, String hdd, String cpu) {

        if ("PC".equalsIgnoreCase(type)) {
            return new PC(ram, hdd, cpu);
        }
        if ("Server".equalsIgnoreCase(type)) {
            return new Server(ram, hdd, cpu);
        }
        return null;
    }
}
