package com.company.models.units;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Archer extends Unit {

    private static final Integer HEALTH = 25;
    private static final Integer DAMAGE = 7;

    public Archer() {
        super(HEALTH, DAMAGE);
    }

}
