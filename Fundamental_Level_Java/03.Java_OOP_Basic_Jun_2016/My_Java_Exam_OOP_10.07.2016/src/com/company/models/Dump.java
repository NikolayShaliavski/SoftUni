package com.company.models;

import com.company.models.hardware.Hardware;
import com.company.models.hardware.HeavyHardware;
import com.company.models.hardware.PowerHardware;

import java.util.ArrayList;
import java.util.List;

public class Dump {

    private List<Hardware> dumpedHardware;

    public Dump() {
        this.dumpedHardware = new ArrayList<>();
    }

    public void addHardware(Hardware hardware) {
        this.dumpedHardware.add(hardware);
    }

    public Hardware tryRestoreHardware(String hardwareName) {
        Hardware hardware = null;
        for (Hardware hardwareComponent : this.dumpedHardware) {
            if (hardwareComponent.getName().equals(hardwareName)) {
                hardware = hardwareComponent;
                this.dumpedHardware.remove(hardwareComponent);
                break;
            }
        }
        return hardware;
    }

    public void destroy(String hardwareName) {
        for (Hardware hardwareComponent : this.dumpedHardware) {
            if (hardwareComponent.getName().equals(hardwareName)) {
                this.dumpedHardware.remove(hardwareComponent);
                break;
            }
        }
    }

    public String analyze() {
        StringBuilder builder = new StringBuilder();
        builder.append("Dump Analysis").
                append(System.lineSeparator()).
                append(String.format("Power Hardware Components: %d", this.calculatePowerHardware())).
                append(System.lineSeparator()).
                append(String.format("Heavy Hardware Components: %d", this.calculateHeavyHardware())).
                append(System.lineSeparator()).
                append(String.format("Express Software Components: %d", this.calculateExpressSoftware())).
                append(System.lineSeparator()).
                append(String.format("Light Software Components: %d", this.calculateLightSoftware())).
                append(System.lineSeparator()).
                append(String.format("Total Dumped Memory: %d", this.calculateDumpedMemory())).
                append(System.lineSeparator()).
                append(String.format("Total Dumped Capacity: %d", this.calculateDumpedCapacity()));

        return builder.toString();
    }

    private Integer calculateDumpedCapacity() {
        return this.dumpedHardware.stream().
                mapToInt(hardware -> hardware.getUsedCapacity()).sum();
    }

    private Integer calculateDumpedMemory() {
        return this.dumpedHardware.stream().
                mapToInt(hardware -> hardware.getUsedMemory()).sum();
    }

    private Integer calculateLightSoftware() {
        Integer count = 0;
        for (Hardware hardware : dumpedHardware) {
            count += hardware.calculateLightComponents();
        }
        return count;
    }

    private Integer calculateExpressSoftware() {
        Integer count = 0;
        for (Hardware hardware : dumpedHardware) {
            count += hardware.calculateExpressComponents();
        }
        return count;
    }

    private Integer calculateHeavyHardware() {
        Integer count = 0;
        for (Hardware hardware : dumpedHardware) {
            if (hardware instanceof HeavyHardware) {
                count++;
            }
        }
        return count;
    }

    private Integer calculatePowerHardware() {
        Integer count = 0;
        for (Hardware hardware : dumpedHardware) {
            if (hardware instanceof PowerHardware) {
                count++;
            }
        }
        return count;
    }
}
