package creational.factory;

import creational.factory.entities.Computer;
import creational.factory.factoires.ComputerFactory;
import creational.factory.factoires.abstractFactory.PCFactory;
import creational.factory.factoires.abstractFactory.ServerFactory;
import creational.factory.factoires.abstractFactory.StaticComputerFactory;

public class Main {

    public static void main(String[] args) {

        //Factory pattern with only one factory class
        Computer pc = ComputerFactory.getComputer("pC", "2 GB", "500 GB", "2.4 GHz");
        Computer server = ComputerFactory.getComputer("server", "16 GB", "1 TB", "2.9 GHz");
        System.out.println("Factory PC Config::" + pc);
        System.out.println("Factory Server Config::" + server);

        testAbstractFactory();
    }

    //Factory pattern with factories for each entity
    private static void testAbstractFactory() {

        Computer pc = StaticComputerFactory.getComputer(new PCFactory("2 GB", "500 GB", "2.4 GHz"));
        Computer server = StaticComputerFactory.getComputer(new ServerFactory("16 GB", "1 TB", "2.9 GHz"));
        System.out.println("AbstractFactory PC Config::" + pc);
        System.out.println("AbstractFactory Server Config::" + server);
    }
}
