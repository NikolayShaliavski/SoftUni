package problem_10.contracts;

/**
 * Created by Nikolay Shalyavski on 23.7.2016 г..
 */
public interface Weapon extends Updatable, Comparable<Weapon> {

    String getName();
    int getMinDamage();
    int getMaxDamage();
    int getStrength();
    int getAgility();
    int getVitality();
    double getLevel();
    void addGems(int index, Gem gem);
    void removeGems(int index);
    String strongerToString();
}
