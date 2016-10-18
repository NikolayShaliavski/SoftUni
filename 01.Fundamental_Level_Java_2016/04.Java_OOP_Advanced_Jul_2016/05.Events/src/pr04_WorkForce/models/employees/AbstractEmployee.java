package pr04_WorkForce.models.employees;

import pr04_WorkForce.contracts.Employee;

public abstract class AbstractEmployee implements Employee {

    private String name;
    private int workHours;

    protected AbstractEmployee(String name,
                               int workHours) {
        this.name = name;
        this.workHours = workHours;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getWorkHours() {
        return this.workHours;
    }
}
