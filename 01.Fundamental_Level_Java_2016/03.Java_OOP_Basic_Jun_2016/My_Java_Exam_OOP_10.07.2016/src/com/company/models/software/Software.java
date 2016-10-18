package com.company.models.software;

public abstract class Software {

    private String name;
    private String type;
    private Integer capacityConsumption;
    private Integer memoryConsumption;

    Software(String name,
             String type) {
        this.setName(name);
        this.setType(type);
    }

    public String getName() {
        return this.name;
    }

    public Integer getCapacityConsumption() {
        return this.capacityConsumption;
    }

    public Integer getMemoryConsumption() {
        return this.memoryConsumption;
    }

    protected void setCapacityConsumption(Integer capacityConsumption) {
        this.capacityConsumption = capacityConsumption;
    }

    protected void setMemoryConsumption(Integer memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setType(String type) {
        this.type = type;
    }
}
