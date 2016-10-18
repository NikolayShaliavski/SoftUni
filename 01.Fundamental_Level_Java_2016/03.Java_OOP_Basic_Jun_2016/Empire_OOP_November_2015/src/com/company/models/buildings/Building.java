package com.company.models.buildings;

import com.company.IO.Writer;
import com.company.models.treasures.Gold;
import com.company.models.treasures.Steel;
import com.company.models.treasures.Treasure;
import com.company.models.units.Archer;
import com.company.models.units.Swordsman;
import com.company.models.units.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 8.7.2016 г..
 */
public abstract class Building {
    private List<Unit> units;
    private List<Treasure> treasures;

    private int liveCounter;

    private int turnsToCreateUnit;;
    private int turnsToCreateTreasure;

    private int unitCounter;
    private int treasureCounter;
    private int treasureQuantity;

    private String unitType;
    private String treasureType;

    Building(int turnsToCreateUnit,
             int turnsToCreateTreasure,
             String unitType,
             String treasureType,
             int treasureQuantity) {
        this.units = new ArrayList<>();
        this.treasures = new ArrayList<>();

        this.turnsToCreateUnit = turnsToCreateUnit;
        this.turnsToCreateTreasure = turnsToCreateTreasure;
        this.unitCounter = turnsToCreateUnit + 1;
        this.treasureCounter = turnsToCreateTreasure + 1;

        this.unitType = unitType;
        this.treasureType = treasureType;
        this.treasureQuantity = treasureQuantity;

        this.liveCounter = -1;
    }

    public String getUnitType() {
        return this.unitType;
    }

    public String getTreasureType() {
        return this.treasureType;
    }

    public int getTreasureQuantity() {
        return this.treasureQuantity;
    }

    public boolean tryCreateUnit() {
        if (this.unitCounter == 0) {
            this.unitCounter = this.turnsToCreateUnit;
            Unit unit = null;
            if (this.unitType.equals("Swordsman")) {
                unit = new Swordsman();
            } else if (this.unitType.equals("Archer")) {
                unit = new Archer();
            }
            this.units.add(unit);
            return true;
        }
        return false;
    }

    public boolean tryCreateTreasure() {
        if (this.treasureCounter == 0) {
            this.treasureCounter = this.turnsToCreateTreasure;
            Treasure treasure = null;
            if (this.treasureType.equals("Gold")) {
                treasure = new Gold(this.treasureQuantity);
            } else if (this.treasureType.equals("Steel")) {
                treasure = new Steel(this.treasureQuantity);
            }
            this.treasures.add(treasure);
            return true;
        }
        return false;
    }

    public void print() {
        Writer.write(String.format("--%s: %d turns (%d turns until %s, %d turns until %s)",
                this.getClass().getSimpleName(),
                this.liveCounter,
                this.unitCounter,
                this.getUnitType(),
                this.treasureCounter,
                this.treasureType));
    }

    public void update() {
        this.liveCounter++;
        this.unitCounter--;
        this.treasureCounter--;
    }
}
