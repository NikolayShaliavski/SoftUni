package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public class PropertyEmergency extends BaseEmergency implements Emergency {

    private Integer propertyDamage;

    public PropertyEmergency(String description,
                             EmergencyLevel emergencyLevel,
                             RegistrationTime registrationTimeImpl,
                             Integer propertyDamage) {
        super(description, emergencyLevel, registrationTimeImpl);
        this.propertyDamage = propertyDamage;
    }

    @Override
    public Integer getResultAfterProcessing() {
        return this.propertyDamage;
    }
}
