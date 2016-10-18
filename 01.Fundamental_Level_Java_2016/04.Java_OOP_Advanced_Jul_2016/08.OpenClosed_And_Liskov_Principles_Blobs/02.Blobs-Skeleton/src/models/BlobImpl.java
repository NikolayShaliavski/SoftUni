package models;

import interfaces.Attack;
import interfaces.Behavior;
import interfaces.Blob;

public class BlobImpl implements Blob {

    private String name;
    private int currentHealth;
    private int damage;
    private Behavior behavior;
    private Attack attack;
    //private int triggerCount;

    private int initialHealth;
    private int initialDamage;

    public BlobImpl(String name,
                    int health,
                    int damage,
                    Behavior behavior,
                    Attack attack) {
        this.name = name;
        this.currentHealth = health;
        this.damage = damage;
        this.behavior = behavior;
        this.attack = attack;

        this.initialHealth = health;
        this.initialDamage = damage;
    }

    @Override
    public int getHealth() {
        return this.currentHealth;
    }

    @Override
    public void setHealth(int health) {
        this.currentHealth = health;

        if (this.currentHealth <= 0) {
            this.currentHealth = 0;
        }

//        if (this.getHealth() <= this.initialHealth / 2) {
//            this.triggerBehavior();
//        }
    }

    @Override
    public Behavior getBehavior() {
        return this.behavior;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(int currentDamage) {
        this.damage = currentDamage;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String update() {
        String result = "";
        if (this.getHealth() <= this.initialHealth / 2) {
            result = this.triggerBehavior();
        }
        if (this.behavior.isTriggered()) {
            this.behavior.applyRecurrentEffect(this);
        }
        return result;
    }

    @Override
    public String toString() {
        if (this.getHealth() <= 0) {
            return String.format("Blob %s KILLED", this.getName());
        }

        return String.format("Blob %s: %s HP, %s Damage", this.getName(), this.getHealth(), this.getDamage());
    }

    @Override
    public void attack(Blob target) {
        this.attack.execute(this, target);
    }

    private String triggerBehavior() {
        if (!this.behavior.isTriggered()) {
            this.behavior.trigger(this);
            return String.format("Blob %s toggled %sBehavior",
                    this.getName(),
                    this.getBehavior().getClass().getSimpleName());
        }
        return "";
    }
}
