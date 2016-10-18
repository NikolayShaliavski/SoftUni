package core.commands;

import core.annotations.InjectType;
import core.system.ManagementSystem;

public class ProcessEmergenciesCommand extends Command implements Executable {

    @InjectType
    private String type;

    public ProcessEmergenciesCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        return super.getManagementSystem().processEmergencies(this.type);
    }
}
