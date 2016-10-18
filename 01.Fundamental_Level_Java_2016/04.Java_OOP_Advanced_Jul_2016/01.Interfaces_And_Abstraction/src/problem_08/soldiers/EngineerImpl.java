package problem_08.soldiers;

import problem_08.duties.RepairImpl;
import problem_08.interfaces.Engineer;

import java.util.LinkedHashSet;
import java.util.Set;

public class EngineerImpl extends SpecializedSoldierImpl implements Engineer {

    private Set<RepairImpl> repairs;

    public EngineerImpl(String firstName,
                 String lastName,
                 String id,
                 Double salary,
                 String corps) {
        super(firstName, lastName, id, salary, corps);
        this.repairs = new LinkedHashSet<>();
    }

    @Override
    public void addRepairs(RepairImpl repairImpl) {
        this.repairs.add(repairImpl);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).
            append("Repairs:").
                append(System.lineSeparator());
        for (RepairImpl repair : this.repairs) {
            builder.append(repair.toString());
        }
        return builder.toString().trim();
    }
}
