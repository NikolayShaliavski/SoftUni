package pr01_EventImplementation.models;

import pr01_EventImplementation.contracts.NameChangeListener;
import pr01_EventImplementation.events.NameChange;

public class Handler implements NameChangeListener {

    @Override
    public void handleChangedName(NameChange event) {
        System.out.println(
                "Dispatcher's name changed to " + event.getChangedName() + ".");
    }
}
