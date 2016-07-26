package problem_10.factories;

import problem_10.contracts.Weapon;
import problem_10.enums.WeaponsEnum;
import problem_10.models.weapons.Axe;
import problem_10.models.weapons.Knife;
import problem_10.models.weapons.Sword;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class WeaponFactory {

    public static Weapon createWeapon(String weaponName, WeaponsEnum type) {

        Weapon weapon = null;
        switch (type) {
            case AXE:
                weapon = new Axe(weaponName);
                break;
            case SWORD:
                weapon = new Sword(weaponName);
                break;
            case KNIFE:
                weapon = new Knife(weaponName);
                break;
        }
        return weapon;
    }
}
