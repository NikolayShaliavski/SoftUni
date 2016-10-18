package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public class HealthEmergency extends BaseEmergency implements Emergency {

    private Integer casualties;

    public HealthEmergency(String description,
                           EmergencyLevel emergencyLevel,
                           RegistrationTime registrationTimeImpl,
                           Integer casualties) {
        super(description, emergencyLevel, registrationTimeImpl);
        this.casualties = casualties;
    }

    @Override
    public Integer getResultAfterProcessing() {
        return this.casualties;
    }
}
