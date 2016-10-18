package core.commands;

import core.system.ManagementSystem;

public abstract class Command implements Executable {

    private ManagementSystem managementSystem;

    protected Command(ManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    protected ManagementSystem getManagementSystem() {
        return this.managementSystem;
    }
}
