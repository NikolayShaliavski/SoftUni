package problem_08.soldiers;

import problem_08.interfaces.SpecializedSoldier;

public abstract class SpecializedSoldierImpl extends SoldierImpl implements SpecializedSoldier {

    private static final String CORP_01 = "Airforces";
    private static final String CORP_02 = "Marines";

    private Double salary;
    private String corps;

    SpecializedSoldierImpl(String firstName,
                           String lastName,
                           String id,
                           Double salary,
                           String corps) {
        super(firstName, lastName, id);
        this.setSalary(salary);
        this.setCorps(corps);
    }

    @Override
    public Double getSalary() {
        return this.salary;
    }

    private void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String getCorps() {
        return this.corps;
    }

    private void setCorps(String corps) {
        if (!corps.equals(CORP_01) && !corps.equals(CORP_02)) {
            throw new IllegalArgumentException("Corps don't match to the soldier.");
        }
        this.corps = corps;
    }

    @Override
    public String toString() {
        return String.format(
                "%s Salary: %.2f%nCorps: %s%n",
                    super.toString(),
                        this.salary,
                            this.corps);
    }
}
