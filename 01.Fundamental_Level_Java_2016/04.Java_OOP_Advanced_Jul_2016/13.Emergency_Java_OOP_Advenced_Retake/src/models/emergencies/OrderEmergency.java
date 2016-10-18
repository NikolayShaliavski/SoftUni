package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public class OrderEmergency extends BaseEmergency implements Emergency {

    private String status;

    public OrderEmergency(String description,
                          EmergencyLevel emergencyLevel,
                          RegistrationTime registrationTimeImpl,
                          String status) {
        super(description, emergencyLevel, registrationTimeImpl);
        this.status = status;
    }

    @Override
    public Integer getResultAfterProcessing() {
        return this.status.equals("Special") ? 1 : 0;
    }
}
