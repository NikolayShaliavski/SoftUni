package pr02_King_Gambit.contracts;

import pr02_King_Gambit.events.DieEvent;

import java.util.List;

public interface Observable {

    void addObserver(Observer observer);
    void handleDeadObserver(DieEvent dieEvent);
    void fireAttack();
    List<Observer> getObservers();
}
