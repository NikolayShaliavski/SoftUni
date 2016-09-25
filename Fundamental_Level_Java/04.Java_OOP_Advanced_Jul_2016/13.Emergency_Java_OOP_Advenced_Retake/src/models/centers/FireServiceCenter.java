package models.centers;

public class FireServiceCenter extends BaseEmergencyCenter implements EmergencyCenter {

    public FireServiceCenter(String name,
                             Integer amountOfMaximumEmergencies) {
        super(name, amountOfMaximumEmergencies);
    }
}
