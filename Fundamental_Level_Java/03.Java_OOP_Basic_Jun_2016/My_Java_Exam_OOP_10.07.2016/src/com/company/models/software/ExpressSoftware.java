package com.company.models.software;

public class ExpressSoftware extends Software {
    private static final String TYPE = "Express";

    public ExpressSoftware(String name,
                    Integer capacityConsumption,
                    Integer memoryConsumption) {
        super(name, TYPE);
        this.setCapacityConsumption(capacityConsumption);
        this.setMemoryConsumption(memoryConsumption);
    }

    @Override
    protected void setCapacityConsumption(Integer capacityConsumption) {
        super.setCapacityConsumption(capacityConsumption);
    }

    @Override
    protected void setMemoryConsumption(Integer memoryConsumption) {
        super.setMemoryConsumption(memoryConsumption * 2);
    }
}
