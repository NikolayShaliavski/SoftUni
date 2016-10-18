package models.centers;

public class MedicalServiceCenter extends BaseEmergencyCenter implements EmergencyCenter {

    public MedicalServiceCenter(String name,
                                Integer amountOfMaximumEmergencies) {
        super(name, amountOfMaximumEmergencies);
    }
}
