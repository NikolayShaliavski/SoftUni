package pr01_EventImplementation.contracts;

import pr01_EventImplementation.events.NameChange;

public interface NameChangeListener {

    void handleChangedName(NameChange event);
}
