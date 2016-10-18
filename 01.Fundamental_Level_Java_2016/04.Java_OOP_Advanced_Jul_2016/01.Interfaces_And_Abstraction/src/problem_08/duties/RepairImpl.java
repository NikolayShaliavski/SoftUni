package problem_08.duties;

import problem_08.interfaces.Repair;

public class RepairImpl implements Repair {

    private String partName;
    private Integer hours;

    public RepairImpl(String partName, Integer hours) {
        this.setPartName(partName);
        this.setHours(hours);
    }

    @Override
    public String getPartName() {
        return this.partName;
    }

    private void setPartName(String partName) {
        this.partName = partName;
    }

    @Override
    public Integer getHours() {
        return this.hours;
    }

    private void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return String.format("  Part Name: %s Hours Worked: %d%n", this.partName, this.hours);
    }
}
