package com.company.models;

import com.company.IO.Writer;
import com.company.models.buildings.Building;

import java.util.*;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Empire {

    private List<Building> buildings;
    private Map<String, Integer> units;
    private Map<String, Integer> treasures;

    public Empire() {
        this.buildings = new LinkedList<>();
        this.units = new LinkedHashMap<>();
        this.setTreasury();
    }

    public void addNewBuilding(Building building) {
        this.update();
        this.buildings.add(building);
    }

    public void update() {
        for (Building building : buildings) {
            building.update();
            if (building.tryCreateUnit()) {
                String unitType = building.getUnitType();
                if (!this.units.containsKey(unitType)) {
                    this.units.put(unitType, 1);
                } else {
                    this.units.put(unitType, this.units.get(unitType) + 1);
                }
            }
            if (building.tryCreateTreasure()) {
                String treasureType = building.getTreasureType();
                if (!this.treasures.containsKey(treasureType)) {
                    this.treasures.put(treasureType, building.getTreasureQuantity());
                } else {
                    this.treasures.put(treasureType,
                            this.treasures.get(treasureType) + building.getTreasureQuantity());
                }
            }
        }
    }

    public void print() {
        this.update();
        Writer.write("Treasury:");
        for (Map.Entry<String, Integer> treasure : this.treasures.entrySet()) {
            Writer.write(String.format("--%s: %d", treasure.getKey(), treasure.getValue()));
        }
        Writer.write("Buildings:");
        if (this.buildings.size() == 0) {
            Writer.write("N/A");
        } else {
            for (Building building : this.buildings) {
                building.print();
            }
        }
        Writer.write("Units:");
        if (this.units.size() == 0) {
            Writer.write("N/A");
        } else {
            for (Map.Entry<String, Integer> unit : this.units.entrySet()) {
                Writer.write(String.format("--%s: %d", unit.getKey(), unit.getValue()));
            }
        }
    }

    private void setTreasury() {
        this.treasures = new TreeMap<>();
        this.treasures.put("Gold", 0);
        this.treasures.put("Steel", 0);
    }

}
