package problem_10.contracts;

import problem_10.enums.WeaponsEnum;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public interface WeaponCreator {

    Weapon createWeapon(String[] params, WeaponsEnum type);
}
