package pr01_EventImplementation.models;

import pr01_EventImplementation.contracts.NameChangeListener;
import pr01_EventImplementation.events.NameChange;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    private String name;
    private List<NameChangeListener> listeners;

    public Dispatcher(String name) {
        this.name = name;
        this.listeners = new ArrayList<>();
    }

    public void addNameChangeListener(NameChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeNameChangeListener(NameChangeListener listener) {
        this.listeners.remove(listener);
    }

    public void setName(String name) {
        this.name = name;
        this.fireNameChangeEvent();
    }

    private void fireNameChangeEvent() {
        NameChange event = new NameChange(this, this.name);
        for (NameChangeListener listener : this.listeners) {
            listener.handleChangedName(event);
        }
    }
}
