package com.company.models.factories;

import com.company.models.hardware.Hardware;
import com.company.models.hardware.HeavyHardware;
import com.company.models.hardware.PowerHardware;

public class HardwareComponentFactory {

    public Hardware createHardware(String command, String[] componentTokens) {
        String type = command.replace("Register", "").replace("Hardware", "");

        Hardware hardware = null;

        String name = componentTokens[0];
        Integer capacity = Integer.valueOf(componentTokens[1]);
        Integer memory = Integer.valueOf(componentTokens[2]);

        switch (type) {
            case "Power":
                hardware = new PowerHardware(name, capacity, memory);
                break;
            case "Heavy":
                hardware = new HeavyHardware(name, capacity, memory);
                break;
            default:
                break;
        }
        return hardware;
    }
}
