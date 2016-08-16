package pr04_WorkForce.contracts;

public interface JobObservable {

    String getName();
    int getRequiredHours();
    void setListener(JobCompletedListener listener);
    String update();
    void fireJobCompleteEvent();
    String reportStatus();
}
