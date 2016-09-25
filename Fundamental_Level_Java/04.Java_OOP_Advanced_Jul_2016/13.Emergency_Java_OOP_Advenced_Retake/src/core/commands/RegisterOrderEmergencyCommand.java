package core.commands;

import core.annotations.InjectParams;
import core.system.ManagementSystem;
import enums.EmergencyLevel;
import models.emergencies.Emergency;
import models.emergencies.OrderEmergency;
import utils.RegistrationTime;
import utils.RegistrationTimeImpl;

public class RegisterOrderEmergencyCommand extends Command implements Executable {

    @InjectParams
    private String[] params;
    private RegistrationTime registrationTime;
    private Emergency emergency;

    public RegisterOrderEmergencyCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String description = this.params[1];
        EmergencyLevel level = EmergencyLevel.valueOf(this.params[2]);
        String status = this.params[4];
        this.createRegistrationTime(this.params[3]);

        this.emergency = new OrderEmergency(description, level, this.registrationTime, status);
        return super.getManagementSystem().registerOrderEmergency(this.emergency);
    }

    private void createRegistrationTime(String registrationTime) {
        this.registrationTime = new RegistrationTimeImpl(registrationTime);
    }
}
