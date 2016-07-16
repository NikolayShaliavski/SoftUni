package problem_03.models;

import problem_03.interfaces.Car;

/**
 * Created by Nick on 11.7.2016 г..
 */
public class Ferrari implements Car {

    private static final String MODEL = "488-Spider";
    
    private String driverName;

    public Ferrari(String driverName) {
        this.setDriverName(driverName);
    }

    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String pusGas() {
        return "Zadu6avam sA!";
    }

    private String getMODEL() {
        return this.MODEL;
    }

    private void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.getMODEL() + "/")
                .append(this.useBrakes() + "/")
                .append(this.pusGas() + "/")
                .append(this.driverName);

        return builder.toString();
    }
}
