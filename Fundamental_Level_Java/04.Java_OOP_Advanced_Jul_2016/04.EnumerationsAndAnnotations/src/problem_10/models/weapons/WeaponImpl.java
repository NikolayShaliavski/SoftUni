package problem_10.models.weapons;

import problem_10.contracts.Gem;
import problem_10.contracts.Weapon;

/**
 * Created by Nikolay Shalyavski on 23.7.2016 г..
 */
public abstract class WeaponImpl implements Weapon {

    private String name;
    private int minDamage;
    private int maxDamage;
    private int strength;
    private int agility;
    private int vitality;
    private double level;
    private Gem[] gems;

    WeaponImpl(String name,
               int minDamage,
               int maxDamage,
               int sockets) {
        this.setName(name);
        this.setMinDamage(minDamage);
        this.setMaxDamage(maxDamage);
        this.initGems(sockets);
        this.initStartStats();
        this.calculateLevel();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public int getMinDamage() {
        return this.minDamage;
    }

    private void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    @Override
    public int getMaxDamage() {
        return this.maxDamage;
    }

    private void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public int getAgility() {
        return this.agility;
    }

    @Override
    public int getVitality() {
        return this.vitality;
    }

    @Override
    public double getLevel() {
        return this.level;
    }

    @Override
    public void addGems(int index, Gem gem) {
        if (index < 0 || index >= this.gems.length) {
            return;
        }
        this.decreaseDamage(index);
        this.gems[index] = gem;
        this.increaseDamage(gem);
        this.updateStats();
    }

    @Override
    public void removeGems(int index) {
        if (index < 0 || index >= this.gems.length) {
            return;
        }
        this.decreaseDamage(index);
        this.gems[index] = null;
        this.updateStats();
    }

    @Override
    public void updateStats() {
        this.initStartStats();
        for (int i = 0; i < this.gems.length; i++) {
            if (this.gems[i] != null) {
                this.strength += this.gems[i].getStrength();
                this.agility += this.gems[i].getAgility();
                this.vitality += this.gems[i].getVitality();
            }
        }
        this.calculateLevel();
    }

    @Override
    public int compareTo(Weapon otherWeapon) {
        return Double.compare(this.getLevel(), otherWeapon.getLevel());
    }

    @Override
    public String toString() {
        return String.format(
                "%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality",
                this.getName(),
                this.getMinDamage(),
                this.getMaxDamage(),
                this.getStrength(),
                this.getAgility(),
                this.getVitality());
    }

    @Override
    public String strongerToString() {
        return String.format("%s (Item Level: %.1f)",
                this.toString(), this.getLevel());
    }

    private void increaseDamage(Gem gem) {
        int gemStrength = gem.getStrength();
        int gemAgility = gem.getAgility();
        this.increaseMinDamage((gemStrength * 2) + gemAgility);
        this.increaseMaxDamage((gemStrength * 3) + (gemAgility * 4));
    }

    private void increaseMinDamage(int points) {
        this.setMinDamage(this.getMinDamage() + points);
    }

    private void increaseMaxDamage(int points) {
        this.setMaxDamage(this.getMaxDamage() + points);
    }

    private void decreaseDamage(int index) {
        if (this.gems[index] != null) {
            int gemStrength = this.gems[index].getStrength();
            int gemAgility = this.gems[index].getAgility();
            this.decreaseMinDamage((gemStrength * 2) + gemAgility);
            this.decreaseMaxDamage((gemStrength * 3) + (gemAgility * 4));
        }
    }

    private void decreaseMinDamage(int points) {
        this.setMinDamage(this.getMinDamage() - points);
    }

    private void decreaseMaxDamage(int points) {
        this.setMaxDamage(this.getMaxDamage() - points);
    }

    private void calculateLevel() {
        double average = (double) (this.getMinDamage() + this.getMaxDamage()) / 2;
        this.level = average +
                this.getStrength() +
                this.getAgility() +
                this.getVitality();
    }

    private void initGems(int sockets) {
        this.gems = new Gem[sockets];
    }

    private void initStartStats() {
        this.strength = 0;
        this.agility = 0;
        this.vitality = 0;
    }
}
