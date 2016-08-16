package pr04_WorkForce.contracts;

import pr04_WorkForce.events.CompleteJob;

public interface JobCompletedListener {

    void handleJobCompleteEvent(CompleteJob completeJobEvent);
}
