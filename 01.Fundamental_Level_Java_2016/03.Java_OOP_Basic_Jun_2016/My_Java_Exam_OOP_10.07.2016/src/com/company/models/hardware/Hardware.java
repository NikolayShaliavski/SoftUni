package com.company.models.hardware;

import com.company.models.software.ExpressSoftware;
import com.company.models.software.LightSoftware;
import com.company.models.software.Software;

import java.util.LinkedList;
import java.util.List;

public abstract class Hardware {

    private static final Integer START_USED_CAPACITY = 0;
    private static final Integer START_USED_MEMORY = 0;

    private String name;
    private String type;
    private Integer maxCapacity;
    private Integer maxMemory;
    private Integer usedCapacity;
    private Integer usedMemory;

    private List<Software> softWareComponents;

    Hardware(String name,
             String type) {
        this.setName(name);
        this.setType(type);
        this.usedCapacity = START_USED_CAPACITY;
        this.usedMemory = START_USED_MEMORY;

        this.softWareComponents = new LinkedList<>();
    }


    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Integer getMaxCapacity() {
        return this.maxCapacity;
    }

    public Integer getMaxMemory() {
        return this.maxMemory;
    }

    public Integer getUsedCapacity() {
        return this.usedCapacity;
    }

    public Integer getUsedMemory() {
        return this.usedMemory;
    }

    public Integer getSoftwareComponents() {
        return this.softWareComponents.size();
    }

    public void registerSoftware(Software softwareComponent) {
        if (this.usedCapacity + softwareComponent.getCapacityConsumption() <= this.maxCapacity &&
                this.usedMemory + softwareComponent.getMemoryConsumption() <= this.maxMemory) {
            this.softWareComponents.add(softwareComponent);
            this.updateUsedResources();
        }
    }

    public void releaseSoftware(String softwareName) {
        for (int i = 0; i < this.softWareComponents.size(); i++) {
            Software softwareComponent = this.softWareComponents.get(i);
            if (softwareComponent.getName().equals(softwareName)) {
                this.softWareComponents.remove(softwareComponent);
                this.updateUsedResources();
                break;
            }
        }
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hardware Component - %s", this.name)).
                append(System.lineSeparator()).
                append(String.format("Express Software Components - %d", this.calculateExpressComponents())).
                append(System.lineSeparator()).
                append(String.format("Light Software Components - %d", this.calculateLightComponents())).
                append(System.lineSeparator()).
                append(String.format("Memory Usage: %d / %d", this.usedMemory, this.maxMemory)).
                append(System.lineSeparator()).
                append(String.format("Capacity Usage: %d / %d", this.usedCapacity, this.maxCapacity)).
                append(System.lineSeparator()).
                append(String.format("Type: %s", this.type)).
                append(System.lineSeparator()).
                append(String.format("Software Components: %s", this.printSoftwareComponents())).
                append(System.lineSeparator());
        return builder.toString();
    }

    public Integer calculateExpressComponents() {
        int count = 0;
        for (Software softWareComponent : softWareComponents) {
            if (softWareComponent instanceof ExpressSoftware) {
                count++;
            }
        }
        return count;
    }

    public Integer calculateLightComponents() {
        int count = 0;
        for (Software softWareComponent : softWareComponents) {
            if (softWareComponent instanceof LightSoftware) {
                count++;
            }
        }
        return count;
    }

    protected void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    protected void setMaxMemory(Integer maxMemory) {
        this.maxMemory = maxMemory;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setType (String type) {
        this.type = type;
    }

    private void updateUsedResources() {
        this.usedCapacity = this.softWareComponents.stream().
                mapToInt(software -> software.getCapacityConsumption()).sum();

        this.usedMemory = this.softWareComponents.stream().
                mapToInt(software -> software.getMemoryConsumption()).sum();
    }

    private String printSoftwareComponents() {
        StringBuilder builder = new StringBuilder();
        if (this.softWareComponents.size() == 0) {
            builder.append("None");
            return builder.toString();
        }
        for (Software softWareComponent : softWareComponents) {
            builder.append(String.format("%s, ", softWareComponent.getName()));
        }
        return builder.delete(builder.length() - 2, builder.length()).toString();
    }
}
