package com.company.models;

import com.company.models.hardware.Hardware;
import com.company.models.software.Software;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MySystem {

    private static final Integer START_MAX_CAPACITY = 0;
    private static final Integer START_MAX_MEMORY = 0;
    private static final Integer START_USED_CAPACITY = 0;
    private static final Integer START_USED_MEMORY = 0;

    private Integer maxCapacity;
    private Integer maxMemory;
    private Integer usedCapacity;
    private Integer usedMemory;

    private List<Hardware> hardware;

    private Dump dump;

    public MySystem() {
        this.hardware = new ArrayList<>();

        this.maxCapacity = START_MAX_CAPACITY;
        this.maxMemory = START_MAX_MEMORY;
        this.usedCapacity = START_USED_CAPACITY;
        this.usedMemory = START_USED_MEMORY;

        this.dump = new Dump();
    }

    public String analyze() {
        StringBuilder builder = new StringBuilder();
        builder.append("System Analysis").
                append(System.lineSeparator()).
                append(String.format("Hardware Components: %d", this.hardware.size())).
                append(System.lineSeparator()).
                append(String.format("Software Components: %d", this.calculateSoftwareComponents())).
                append(System.lineSeparator()).
                append(String.format("Total Operational Memory: %d / %d", this.usedMemory, this.maxMemory)).
                append(System.lineSeparator()).
                append(String.format("Total Capacity Taken: %d / %d", this.usedCapacity, this.maxCapacity));
        return builder.toString();
    }

    public String split() {
        StringBuilder builder = new StringBuilder();
        List<Hardware> sorted = this.hardware.stream().
                sorted((hardware1, hardware2) -> hardware2.getType().compareTo(hardware1.getType())).
                collect(Collectors.toList());
        for (Hardware hardwareComponent : sorted) {
            builder.append(hardwareComponent.print());
        }
        return builder.toString();
    }

    public void tryDumpHardware(String hardwareName) {
        for (Hardware hardwareComponent : hardware) {
            if (hardwareComponent.getName().equals(hardwareName)) {
                this.dump.addHardware(hardwareComponent);
                this.hardware.remove(hardwareComponent);
                this.updateSystemResources();
                break;
            }
        }

    }

    public void tryRestoreHardware(String hardwareName) {
        Hardware hardwareToRestore = this.dump.tryRestoreHardware(hardwareName);
        if (hardwareToRestore != null) {
            this.hardware.add(hardwareToRestore);
            this.updateSystemResources();
        }
    }

    public void tryDeleteHardware(String hardwareName) {
        this.dump.destroy(hardwareName);
    }

    public void tryAddSoftware(String hardwareName, Software software) {
        for (Hardware hardwareComponent : hardware) {
            if (hardwareComponent.getName().equals(hardwareName)) {
                hardwareComponent.registerSoftware(software);
                this.updateSystemResources();
                break;
            }
        }
    }

    public String dumpAnalyze() {
        return this.dump.analyze();
    }

    public void tryReleaseSoftware(String hardwareName, String softwareName) {
        for (Hardware hardwareComponent : hardware) {
            if (hardwareComponent.getName().equals(hardwareName)) {
                hardwareComponent.releaseSoftware(softwareName);
            }
        }
    }

    public void addHardware(Hardware hardware) {
        this.hardware.add(hardware);
        this.updateSystemResources();
    }

    private void updateSystemResources() {
        this.updateSystemCapacity();
        this.updateSystemMemory();
    }

    private void updateSystemMemory() {
        this.maxMemory = this.hardware.stream().
                mapToInt(hardware -> hardware.getMaxMemory()).sum();
        this.usedMemory = this.hardware.stream().
                mapToInt(hardware -> hardware.getUsedMemory()).sum();
    }

    private void updateSystemCapacity() {
        this.maxCapacity = this.hardware.stream().
                mapToInt(hardware -> hardware.getMaxCapacity()).sum();
        this.usedCapacity = this.hardware.stream().
                mapToInt(hardware -> hardware.getUsedCapacity()).sum();
    }

    private Integer calculateSoftwareComponents() {
        return this.hardware.stream().
                mapToInt(hardware -> hardware.getSoftwareComponents()).sum();
    }
}
