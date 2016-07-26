package problem_10.models;

import problem_10.contracts.Gem;
import problem_10.contracts.Inferno;
import problem_10.contracts.Weapon;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class InfernoRepository implements Inferno {

    private List<Weapon> weapons;

    public InfernoRepository() {
        this.weapons = new LinkedList<>();
    }

    @Override
    public String reportWeapon(String weaponName) {
        Weapon weaponToPrint = searchWeapon(weaponName);
        if (weaponToPrint != null) {
            return weaponToPrint.toString();
        }
        return "Weapon doesn't exists.";
    }

    /**
     *
     * <b>jjkkjjkjkjk</b>
     *
     * @param firstWeapon
     * @param secondWeapon
     * @return
     */
    @Override
    public String compareWeapons(String firstWeapon, String secondWeapon) {
        Weapon weaponFirst = this.searchWeapon(firstWeapon);
        Weapon weponSecond = this.searchWeapon(secondWeapon);
        if (weaponFirst != null && weponSecond != null) {
            int result = weaponFirst.compareTo(weponSecond);
            if (result >= 0) {
                return weaponFirst.strongerToString();
            } else {
                return weponSecond.strongerToString();
            }
        }
        return "Weapon don't exist.";
    }

    @Override
    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    @Override
    public void addGemsToWeapon(String weaponName, int index, Gem gem) {
        Weapon weapon = this.searchWeapon(weaponName);
        if (weapon != null) {
            weapon.addGems(index, gem);
        }
    }

    @Override
    public void removeGemsFromWeapon(String weaponName, int index) {
        Weapon weapon = this.searchWeapon(weaponName);
        if (weapon != null) {
            weapon.removeGems(index);
        }
    }

    @Override
    public Weapon searchWeapon(String weaponName) {
        return this.weapons.stream().
                filter(weapon -> weapon.getName().equals(weaponName)).
                findFirst().get();
    }
}
