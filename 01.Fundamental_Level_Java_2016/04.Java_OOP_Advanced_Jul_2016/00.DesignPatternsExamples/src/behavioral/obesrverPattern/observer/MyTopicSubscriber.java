package behavioral.obesrverPattern.observer;

import behavioral.obesrverPattern.observable.Subject;

public class MyTopicSubscriber implements MyObserver {

    private String name;
    private Subject topic;

    public MyTopicSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        String message = this.topic.getUpdate(this);
        if (message == null) {
            System.out.println(this.name + ":: No new message");
        } else {
            System.out.println(this.name + ":: Consuming message::" + message);
        }
    }

    @Override
    public void setSubject(Subject subject) {
        this.topic = subject;
    }
}
