package problem_08.interfaces;

import problem_08.soldiers.PrivateImpl;

public interface LeutenantGeneral extends Soldier {

    Double getSalary();
    void add(PrivateImpl privateSoldier);
}
