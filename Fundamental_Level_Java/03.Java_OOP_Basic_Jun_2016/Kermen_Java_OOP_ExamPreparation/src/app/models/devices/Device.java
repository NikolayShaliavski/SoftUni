package app.models.devices;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class Device {

    private Double consumption;

    public Device(Double consumption) {
        this.setConsumption(consumption);
    }

    public Double getConsumption() {
        return this.consumption;
    }

    private void setConsumption(Double consumption) {
        if (consumption < 0) {
            throw new IllegalArgumentException("Device consumption cannot be less than zero!");
        }
        this.consumption = consumption;
    }
}
