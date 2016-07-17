package problem_08.soldiers;

import problem_08.interfaces.Private;

public class PrivateImpl extends SoldierImpl implements Private{

    private Double salary;

    public PrivateImpl(String firstName,
                       String lastName,
                       String id,
                       Double salary) {
        super(firstName, lastName, id);
        this.setSalary(salary);
    }

    @Override
    public Double getSalary() {
        return this.salary;
    }

    private void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%s Salary: %.2f", super.toString(), this.salary);
    }
}
