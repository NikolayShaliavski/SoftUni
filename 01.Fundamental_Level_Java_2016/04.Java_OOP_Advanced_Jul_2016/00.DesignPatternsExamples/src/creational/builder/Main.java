package creational.builder;

public class Main {

    public static void main(String[] args) {
        //Using builder to get the object in a single line of code and
        //without any inconsistent state or arguments management issues
        Computer computer =
                new Computer.ComputerBuilder("500 GB", "2 GB").
                setGraphicsCardEnabled(true).
                setBluetoothEnabled(true).
                build();

        System.out.println(computer);
    }
}
