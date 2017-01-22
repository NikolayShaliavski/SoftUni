package behavioral.obesrverPattern.observable;

import behavioral.obesrverPattern.observer.MyObserver;

public interface Subject {

    //method to register ang unregister observers
    void register(MyObserver observer);
    void unregister(MyObserver observer);

    //method to notify observers of change
    void notifyObservers();

    //method to get updates from subject
    String getUpdate(MyObserver obj);
}
