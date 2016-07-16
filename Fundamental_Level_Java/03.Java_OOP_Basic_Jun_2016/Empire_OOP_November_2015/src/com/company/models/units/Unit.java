package com.company.models.units;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class Unit {

    private int health;
    private int damage;

    Unit(int health, int damage) {
        this.setHealth(health);
        this.setDamage(damage);
    }

    private void setHealth(int health) {
        if (health <= 0) {
            throw new IllegalArgumentException("Health cannot be less or equal to zero.");
        }
        this.health = health;
    }

    private void setDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be less than zero.");
        }
        this.damage = damage;
    }
}
