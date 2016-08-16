package pr01_EventImplementation.events;

import java.util.EventObject;

public class NameChange extends EventObject {

    private String changedName;

    public NameChange(Object sender, String changedName) {
        super(sender);
        this.changedName = changedName;
    }

    public String getChangedName() {
        return this.changedName;
    }
}
