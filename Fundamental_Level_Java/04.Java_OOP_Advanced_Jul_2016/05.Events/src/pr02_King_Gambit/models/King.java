package pr02_King_Gambit.models;

import pr02_King_Gambit.contracts.Observable;
import pr02_King_Gambit.contracts.Observer;
import pr02_King_Gambit.events.AttackEvent;
import pr02_King_Gambit.events.DieEvent;

import java.util.LinkedList;
import java.util.List;

public class King implements Observable {

    private String name;
    private List<Observer> observers;

    public King(String name) {
        this.name = name;
        this.observers = new LinkedList<>();
    }


    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void handleDeadObserver(DieEvent dieEvent) {
        this.observers.remove(dieEvent.getSource());
    }

    @Override
    public void fireAttack() {
        AttackEvent attack = new AttackEvent(this);
        System.out.printf("King %s is under attack!%n", this.name);
        for (Observer observer : this.observers) {
            observer.respondToAttack(attack);
        }
    }

    public List<Observer> getObservers() {
        return this.observers;
    }
}
