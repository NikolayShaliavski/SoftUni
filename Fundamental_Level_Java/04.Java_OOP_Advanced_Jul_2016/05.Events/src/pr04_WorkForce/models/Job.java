package pr04_WorkForce.models;

import pr04_WorkForce.contracts.Employee;
import pr04_WorkForce.contracts.JobCompletedListener;
import pr04_WorkForce.contracts.JobObservable;
import pr04_WorkForce.events.CompleteJob;

public class Job implements JobObservable {

    private String name;
    private int requiredHours;
    private Employee employee;
    private JobCompletedListener listener;

    public Job(String name,
               int requiredHours,
               Employee employee) {
        this.setName(name);
        this.setRequiredHours(requiredHours);
        this.employee = employee;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public int getRequiredHours() {
        return this.requiredHours;
    }

    private void setRequiredHours(int requiredHours) {
        this.requiredHours = requiredHours;
    }

    @Override
    public void setListener(JobCompletedListener listener) {
        this.listener = listener;
    }

    @Override
    public String update() {
        this.setRequiredHours(this.getRequiredHours() - this.employee.getWorkHours());
        if (this.getRequiredHours() <= 0) {
            this.fireJobCompleteEvent();
            return String.format("Job %s done!", this.getName());
        }
        return "";
    }

    @Override
    public void fireJobCompleteEvent() {
        CompleteJob completeJobEvent = new CompleteJob(this);
        this.listener.handleJobCompleteEvent(completeJobEvent);
    }

    @Override
    public String reportStatus() {
        return String.format("Job: %s Hours Remaining: %d",
                this.getName(),
                this.requiredHours);
    }
}
