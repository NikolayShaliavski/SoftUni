package com.company.models.buildings;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Archery extends Building {

    private static final Integer UNIT_TURNS = 3;
    private static final Integer TREASURE_TURNS = 2;
    private static final String UNIT_TYPE = "Archer";
    private static final String TREASURE_TYPE = "Gold";
    private static final Integer TREASURE_QUANTITY = 5;

    public Archery() {
        super(UNIT_TURNS, TREASURE_TURNS, UNIT_TYPE, TREASURE_TYPE, TREASURE_QUANTITY);
    }
}
