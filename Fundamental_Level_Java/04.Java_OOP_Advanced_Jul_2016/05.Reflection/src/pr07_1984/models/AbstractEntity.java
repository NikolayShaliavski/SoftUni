package pr07_1984.models;

import pr07_1984.contracts.Event;
import pr07_1984.contracts.Observable;
import pr07_1984.contracts.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntity implements Observable {

    private String id;
    private List<Observer> observers;

    protected AbstractEntity(String id) {
        this.id = id;
        this.observers = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    @Override
    public void addObservers(List<Observer> observers) {
        this.observers.addAll(observers);
    }

    @Override
    public void fireChangeEvent(Event changeEvent) throws IllegalAccessException {
        for (Observer observer : this.observers) {
            observer.handleChangeEvent(changeEvent);
        }
    }
}
