package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public abstract class BaseEmergency implements Emergency {
    private String description;

    private EmergencyLevel emergencyLevel;

    private RegistrationTime registrationTimeImpl;

    protected BaseEmergency(String description,
                            EmergencyLevel emergencyLevel,
                            RegistrationTime registrationTimeImpl) {
        this.setDescription(description);
        this.setEmergencyLevel(emergencyLevel);
        this.setRegistrationTimeImpl(registrationTimeImpl);
    }


    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public EmergencyLevel getEmergencyLevel() {
        return emergencyLevel;
    }

    private void setEmergencyLevel(EmergencyLevel emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public RegistrationTime getRegistrationTimeImpl() {
        return registrationTimeImpl;
    }

    private void setRegistrationTimeImpl(RegistrationTime registrationTimeImpl) {
        this.registrationTimeImpl = registrationTimeImpl;
    }
}
