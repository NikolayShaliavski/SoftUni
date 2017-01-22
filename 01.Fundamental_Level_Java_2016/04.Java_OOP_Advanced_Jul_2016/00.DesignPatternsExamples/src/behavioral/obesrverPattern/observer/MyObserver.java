package behavioral.obesrverPattern.observer;

import behavioral.obesrverPattern.observable.Subject;

public interface MyObserver {

    //method to update the observer, used by subject
    void update();

    //attach with subject to observe
    void setSubject(Subject subject);
}
