package behavioral.obesrverPattern;

import behavioral.obesrverPattern.observable.MyTopic;
import behavioral.obesrverPattern.observer.MyObserver;
import behavioral.obesrverPattern.observer.MyTopicSubscriber;

public class ObserverPatternMain {

    public static void main(String[] args) {

        //create subject
        MyTopic myTopic = new MyTopic();

        //create observers
        MyObserver obj1 = new MyTopicSubscriber("Obj1");
        MyObserver obj2 = new MyTopicSubscriber("Obj2");
        MyObserver obj3 = new MyTopicSubscriber("Obj3");

        //register observers to the subject
        myTopic.register(obj1);
        myTopic.register(obj2);
        myTopic.register(obj3);

        //attach observer to subject
        obj1.setSubject(myTopic);
        obj2.setSubject(myTopic);
        obj3.setSubject(myTopic);

        //check if any update is available
        obj1.update();

        //now send message to subject
        myTopic.postMessage("New Message");
    }
}
