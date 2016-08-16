package pr04_WorkForce.models;

import pr04_WorkForce.contracts.JobCompletedListener;
import pr04_WorkForce.contracts.JobObservable;
import pr04_WorkForce.events.CompleteJob;

import java.util.ArrayList;

public class JobsBg extends ArrayList<JobObservable> implements JobCompletedListener {


    @Override
    public void handleJobCompleteEvent(CompleteJob completeJobEvent) {
        this.remove(completeJobEvent.getSource());
    }
}
