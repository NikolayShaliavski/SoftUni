package core.commands;

import core.annotations.InjectParams;
import core.system.ManagementSystem;
import enums.EmergencyLevel;
import models.emergencies.Emergency;
import models.emergencies.PropertyEmergency;
import utils.RegistrationTime;
import utils.RegistrationTimeImpl;

public class RegisterPropertyEmergencyCommand extends Command implements Executable {

    @InjectParams
    private String[] params;
    private RegistrationTime registrationTime;
    private Emergency emergency;

    public RegisterPropertyEmergencyCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String description = this.params[1];
        EmergencyLevel level = EmergencyLevel.valueOf(this.params[2]);
        Integer propertyDamage = Integer.valueOf(this.params[4]);
        this.createRegistrationTime(this.params[3]);

        this.emergency = new PropertyEmergency(description, level, this.registrationTime, propertyDamage);
        return super.getManagementSystem().registerPropertyEmergency(this.emergency);
    }

    private void createRegistrationTime(String registrationTime) {
        this.registrationTime = new RegistrationTimeImpl(registrationTime);
    }
}
