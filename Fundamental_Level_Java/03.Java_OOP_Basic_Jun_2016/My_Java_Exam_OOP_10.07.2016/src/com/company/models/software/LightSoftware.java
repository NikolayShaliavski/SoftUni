package com.company.models.software;

public class LightSoftware extends Software {
    private static final String TYPE = "Light";

    public LightSoftware(String name,
                  Integer capacityConsumption,
                  Integer memoryConsumption) {
        super(name, TYPE);
        this.setCapacityConsumption(capacityConsumption);
        this.setMemoryConsumption(memoryConsumption);
    }

    @Override
    protected void setCapacityConsumption(Integer capacityConsumption) {
        super.setCapacityConsumption(capacityConsumption + (capacityConsumption / 2));
    }

    @Override
    protected void setMemoryConsumption(Integer memoryConsumption) {
        super.setMemoryConsumption(memoryConsumption - (memoryConsumption / 2));
    }
}
