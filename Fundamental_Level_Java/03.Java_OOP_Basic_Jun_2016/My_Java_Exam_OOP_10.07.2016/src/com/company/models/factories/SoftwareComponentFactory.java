package com.company.models.factories;

import com.company.models.software.ExpressSoftware;
import com.company.models.software.LightSoftware;
import com.company.models.software.Software;

public class SoftwareComponentFactory {

    public Software createSoftware(String command, String[] componentTokens) {
        String type = command.replace("Register", "").replace("Software", "");
        Software software = null;

        String name = componentTokens[1];
        Integer capacity = Integer.valueOf(componentTokens[2]);
        Integer memory = Integer.valueOf(componentTokens[3]);

        switch (type) {
            case "Express":
                software = new ExpressSoftware(name, capacity, memory);
                break;
            case "Light":
                software = new LightSoftware(name, capacity, memory);
                break;
            default:
                break;
        }
        return software;
    }

}
