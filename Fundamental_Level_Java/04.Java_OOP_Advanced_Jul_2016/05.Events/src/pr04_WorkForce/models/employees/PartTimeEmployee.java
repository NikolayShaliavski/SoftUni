package pr04_WorkForce.models.employees;

public class PartTimeEmployee extends AbstractEmployee {

    private static final int PART_TIME_WORK_HOURS = 20;

    public PartTimeEmployee(String name) {
        super(name, PART_TIME_WORK_HOURS);
    }
}
