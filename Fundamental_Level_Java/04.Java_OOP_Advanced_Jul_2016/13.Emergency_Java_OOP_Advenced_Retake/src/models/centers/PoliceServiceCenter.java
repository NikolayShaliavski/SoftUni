package models.centers;

public class PoliceServiceCenter extends BaseEmergencyCenter implements EmergencyCenter {

    public PoliceServiceCenter(String name,
                               Integer amountOfMaximumEmergencies) {
        super(name, amountOfMaximumEmergencies);
    }
}
