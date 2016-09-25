package core.commands;

import core.system.ManagementSystem;

public class EmergencyReportCommand extends Command implements Executable {

    public EmergencyReportCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        return super.getManagementSystem().emergencyReport();
    }
}
