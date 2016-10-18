package core.commands;

import core.annotations.InjectParams;
import core.system.ManagementSystem;
import models.centers.EmergencyCenter;
import models.centers.PoliceServiceCenter;

public class RegisterPoliceServiceCenterCommand extends Command implements Executable {

    @InjectParams
    private String[] params;
    private EmergencyCenter center;

    public RegisterPoliceServiceCenterCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String centerName = this.params[1];
        Integer amountOfEmergencies = Integer.valueOf(this.params[2]);

        this.center = new PoliceServiceCenter(centerName, amountOfEmergencies);
        return super.getManagementSystem().registerPoliceServiceCenter(this.center);
    }
}
