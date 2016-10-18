package pr04_WorkForce.models.employees;

public class StandartEmployee extends AbstractEmployee {

    private static final int STANDART_WORK_HOURS = 40;

    public StandartEmployee(String name) {
        super(name, STANDART_WORK_HOURS);
    }
}
