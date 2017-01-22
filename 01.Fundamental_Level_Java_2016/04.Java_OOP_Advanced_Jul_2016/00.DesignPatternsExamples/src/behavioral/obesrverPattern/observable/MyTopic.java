package behavioral.obesrverPattern.observable;

import behavioral.obesrverPattern.observer.MyObserver;

import java.util.ArrayList;
import java.util.List;

public class MyTopic implements Subject {

    private List<MyObserver> observers;
    private String message;
    private boolean changed;
    private final Object LOCK = new Object();

    public MyTopic() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(MyObserver observer) {
        if (observer == null) {
            throw new NullPointerException("Null Observer");
        }

        synchronized (LOCK) {
            if (!this.observers.contains(observer)) {
                this.observers.add(observer);
            }
        }
    }

    @Override
    public void unregister(MyObserver observer) {
        synchronized (LOCK) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        List<MyObserver> observersLocal = null;

        synchronized (LOCK) {
            if (!this.changed) {
                return;
            }
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (MyObserver observer : observersLocal) {
            observer.update();
        }
    }

    @Override
    public String getUpdate(MyObserver obj) {
        return this.message;
    }

    public void postMessage(String message) {
        System.out.println("Mesage posted to Topic: " + message);
        this.message = message;
        this.changed = true;
        this.notifyObservers();
    }
}
