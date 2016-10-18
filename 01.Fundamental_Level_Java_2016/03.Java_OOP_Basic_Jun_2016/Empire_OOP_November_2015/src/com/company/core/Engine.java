package com.company.core;

import com.company.IO.Reader;
import com.company.IO.Writer;
import com.company.models.Empire;
import com.company.models.buildings.Archery;
import com.company.models.buildings.Barracks;
import com.company.models.buildings.Building;

import java.io.IOException;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Engine implements Runnable {

    @Override
    public void run() {
        Empire empire = new Empire();
        try {
            String line = Reader.read();

            while (!line.equals("armistice")) {
                if (line.equals("skip")) {
                    empire.update();
                    line = Reader.read();
                    continue;
                } else if (line.equals("empire-status")) {
                    empire.print();
                    line = Reader.read();
                    continue;
                }
                String[] buildingTokens = line.split("[\\s]+");
                switch (buildingTokens[1]) {
                    case "barracks":
                        Building building = new Barracks();
                        empire.addNewBuilding(building);
                        break;
                    case "archery":
                        building = new Archery();
                        empire.addNewBuilding(building);
                        break;
                }
                line = Reader.read();
            }
        } catch (IOException ioex) {
            Writer.write(ioex.getMessage());
        }
    }
}
