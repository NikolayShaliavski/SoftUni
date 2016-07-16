package com.company.core;

import com.company.IO.Reader;
import com.company.IO.Writer;
import com.company.models.factories.HardwareComponentFactory;
import com.company.models.MySystem;
import com.company.models.factories.SoftwareComponentFactory;
import com.company.models.hardware.Hardware;
import com.company.models.software.Software;

import java.io.IOException;

public class Engine implements Runnable {

    @Override
    public void run() {

        MySystem mySystem = new MySystem();
        HardwareComponentFactory hardwareFactory = new HardwareComponentFactory();
        SoftwareComponentFactory softwareFactory = new SoftwareComponentFactory();

        try {

            String commandLine = Reader.read();
            while (!commandLine.equals("System Split")) {
                if (commandLine.equals("Analyze()")) {
                    Writer.write(mySystem.analyze());
                    commandLine = Reader.read();
                    continue;
                }
                String command = commandLine.substring(0, commandLine.indexOf("("));
                String[] components = commandLine.substring(commandLine.indexOf("(") + 1, commandLine.indexOf(")"))
                        .split("[,\\s]+");

                if (command.equals("ReleaseSoftwareComponent")) {
                    String hardwareName = components[0];
                    String softwareName = components[1];
                    mySystem.tryReleaseSoftware(hardwareName, softwareName);
                    commandLine = Reader.read();
                    continue;
                } else if (command.equals("Dump")) {
                    String hardwareName = commandLine.replace("Dump(", "").replace(")", "");
                    mySystem.tryDumpHardware(hardwareName);
                    commandLine = Reader.read();
                    continue;
                } else if (command.equals("Restore")) {
                    String hardwareName = commandLine.replace("Restore(", "").replace(")", "");
                    mySystem.tryRestoreHardware(hardwareName);
                    commandLine = Reader.read();
                    continue;
                } else if (command.equals("Destroy")) {
                    String hardwareName = commandLine.replace("Destroy(", "").replace(")", "");
                    mySystem.tryDeleteHardware(hardwareName);
                    commandLine = Reader.read();
                    continue;
                } else if (command.equals("DumpAnalyze")) {
                    Writer.write(mySystem.dumpAnalyze());
                    commandLine = Reader.read();
                    continue;
                } else if (command.endsWith("Hardware")) {
                    Hardware hardware = hardwareFactory.createHardware(command, components);
                    mySystem.addHardware(hardware);
                    commandLine = Reader.read();
                    continue;
                } else if (command.endsWith("Software")) {
                    Software software = softwareFactory.createSoftware(command, components);
                    String hardwareName = components[0];
                    mySystem.tryAddSoftware(hardwareName, software);
                    commandLine = Reader.read();
                    continue;
                }
            }

            Writer.write(mySystem.split());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
