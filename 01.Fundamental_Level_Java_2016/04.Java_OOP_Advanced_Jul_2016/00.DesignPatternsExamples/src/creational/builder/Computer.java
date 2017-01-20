package creational.builder;

public class Computer {

    //required parameters
    private String RAM;
    private String HDD;

    //optional parameters
    private Boolean isGraphicsCardEnabled;
    private Boolean isBluetoothEnabled;

    public String getRAM() {
        return this.RAM;
    }

    public String getHDD() {
        return this.HDD;
    }

    public Boolean isGraphicsCardEnabled() {
        return this.isGraphicsCardEnabled;
    }

    public Boolean isBluetoothEnabled() {
        return this.isBluetoothEnabled;
    }

    private Computer(ComputerBuilder computerBuilder) {
        this.RAM = computerBuilder.RAM;
        this.HDD = computerBuilder.HDD;
        this.isGraphicsCardEnabled = computerBuilder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = computerBuilder.isBluetoothEnabled;
    }

    public static class ComputerBuilder {

        //required parameters
        private String RAM;
        private String HDD;

        //optional parameters
        private Boolean isGraphicsCardEnabled;
        private Boolean isBluetoothEnabled;

        public ComputerBuilder(String RAM, String HDD) {
            this.RAM = RAM;
            this.HDD = HDD;
        }

        public ComputerBuilder setGraphicsCardEnabled(Boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(Boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "RAM='" + RAM + '\'' +
                ", HDD='" + HDD + '\'' +
                ", isGraphicsCardEnabled=" + isGraphicsCardEnabled +
                ", isBluetoothEnabled=" + isBluetoothEnabled +
                '}';
    }
}
