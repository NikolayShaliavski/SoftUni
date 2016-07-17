package problem_08.soldiers;

import problem_08.interfaces.LeutenantGeneral;

import java.util.ArrayList;
import java.util.List;

public class LeutenantGeneralImpl extends SoldierImpl implements LeutenantGeneral {

    private Double salary;
    private List<PrivateImpl> army;

    public LeutenantGeneralImpl(String firstName,
                         String lastName,
                         String id,
                         Double salary) {
        super(firstName, lastName, id);
        this.setSalary(salary);
        this.army = new ArrayList<>();
    }

    @Override
    public Double getSalary() {
        return this.salary;
    }

    private void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public void add(PrivateImpl privateSoldier) {
        this.army.add(privateSoldier);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(
                "%s Salary: %.2f%n",
                    super.toString(),
                        this.salary)).
                            append("Privates:").
                                append(System.lineSeparator());
        for (PrivateImpl aPrivate : this.army) {
            builder.append("  " + aPrivate.toString()).
                    append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
