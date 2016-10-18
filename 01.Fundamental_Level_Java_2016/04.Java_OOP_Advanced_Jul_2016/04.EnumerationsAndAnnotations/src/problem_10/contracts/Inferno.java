package problem_10.contracts;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public interface Inferno {

    void addWeapon(Weapon weapon);
    void addGemsToWeapon(String weaponName, int index, Gem gem);
    void removeGemsFromWeapon(String weaponName, int index);
    Weapon searchWeapon(String weaponName);
    String reportWeapon(String weaponName);
    String compareWeapons(String firstWeapon, String secondWeapon);
}
