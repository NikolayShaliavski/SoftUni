package com.company.models.hardware;

public class PowerHardware extends Hardware {
    private static final String TYPE = "Power";

    public PowerHardware(String name,
                  Integer maxCapacity,
                  Integer maxMemory) {
        super(name, TYPE);
        this.setMaxCapacity(maxCapacity);
        this.setMaxMemory(maxMemory);
    }

    @Override
    protected void setMaxCapacity(Integer maxCapacity) {
        super.setMaxCapacity(maxCapacity - ((maxCapacity * 3) / 4));
    }

    @Override
    protected void setMaxMemory(Integer maxMemory) {
        super.setMaxMemory(maxMemory + ((maxMemory * 3) / 4));
    }
}
