package com.company.models.units;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Swordsman extends Unit {

    private static final Integer HEALTH = 40;
    private static final Integer DAMAGE = 13;

    public Swordsman() {
        super(HEALTH, DAMAGE);
    }
}
