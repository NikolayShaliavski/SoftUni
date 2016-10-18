package com.company.models.treasures;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Gold extends Treasure {
    private static final String TYPE = "Gold";

    public Gold(int quantity) {
        super(quantity, TYPE);
    }
}
