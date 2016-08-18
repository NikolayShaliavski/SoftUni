package models.behaviors;

import interfaces.Behavior;

public abstract class AbstractBehavior implements Behavior {

    protected boolean isTriggered;
    protected boolean toDelayRecurrentEffect;

    public AbstractBehavior() {
        this.toDelayRecurrentEffect = true;
    }
}
