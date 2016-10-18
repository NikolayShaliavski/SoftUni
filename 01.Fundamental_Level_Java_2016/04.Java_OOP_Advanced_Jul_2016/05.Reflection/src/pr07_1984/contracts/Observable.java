package pr07_1984.contracts;

import java.util.List;

public interface Observable {

    String getName();
    String getId();
    void addObservers(List<Observer> observers);
    void fireChangeEvent(Event changeEvent) throws IllegalAccessException;
}
