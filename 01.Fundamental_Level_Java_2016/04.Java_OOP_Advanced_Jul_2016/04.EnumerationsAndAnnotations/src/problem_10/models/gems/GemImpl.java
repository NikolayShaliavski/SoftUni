package problem_10.models.gems;

import problem_10.contracts.Gem;

/**
 * Created by Nikolay Shalyavski on 23.7.2016 г..
 */
public abstract class GemImpl implements Gem {

    private int strength;
    private int agility;
    private int vitality;

    GemImpl(int strength,
            int agility,
            int vitality) {
        this.setStrength(strength);
        this.setAgility(agility);
        this.setVitality(vitality);
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getAgility() {
        return this.agility;
    }

    private void setAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public int getVitality() {
        return this.vitality;
    }

    private void setVitality(int vitality) {
        this.vitality = vitality;
    }
}
