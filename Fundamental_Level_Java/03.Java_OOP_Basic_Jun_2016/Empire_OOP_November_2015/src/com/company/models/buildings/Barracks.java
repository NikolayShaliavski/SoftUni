package com.company.models.buildings;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Barracks extends Building {

    private static final Integer UNIT_TURNS = 4;
    private static final Integer TREASURE_TURNS = 3;
    private static final String UNIT_TYPE = "Swordsman";
    private static final String TREASURE_TYPE = "Steel";
    private static final Integer TREASURE_QUANTITY = 10;

    public Barracks() {
        super(UNIT_TURNS, TREASURE_TURNS, UNIT_TYPE, TREASURE_TYPE, TREASURE_QUANTITY);
    }
}
