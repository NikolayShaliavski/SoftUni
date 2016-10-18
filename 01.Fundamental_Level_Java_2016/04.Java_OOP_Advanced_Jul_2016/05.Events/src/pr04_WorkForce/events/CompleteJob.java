package pr04_WorkForce.events;

import java.util.EventObject;

public class CompleteJob extends EventObject {

    public CompleteJob(Object source) {
        super(source);
    }
}
