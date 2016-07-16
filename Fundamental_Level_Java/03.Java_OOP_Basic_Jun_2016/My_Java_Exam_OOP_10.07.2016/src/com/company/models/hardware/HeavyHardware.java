package com.company.models.hardware;

public class HeavyHardware extends Hardware {
    private static final String TYPE = "Heavy";



    public HeavyHardware(String name,
                  Integer maxCapacity,
                  Integer maxMemory) {
        super(name, TYPE);
        this.setMaxCapacity(maxCapacity);
        this.setMaxMemory(maxMemory);
    }

    @Override
    protected void setMaxCapacity(Integer maxCapacity) {
        super.setMaxCapacity(maxCapacity * 2);
    }

    @Override
    protected void setMaxMemory(Integer maxMemory) {
        super.setMaxMemory(maxMemory - (maxMemory / 4));
    }
}
