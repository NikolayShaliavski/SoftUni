package problem_08.interfaces;

import problem_08.duties.RepairImpl;

public interface Engineer extends SpecializedSoldier {

    void addRepairs(RepairImpl repairImpl);
}
