package models.behaviors;

import models.BlobImpl;

public class Inflated extends AbstractBehavior {

    private static final int INFLATED_HEALTH_ADDITION = 50;
    private static final int INFLATED_HEALTH_DECREMENT = 10;

    public Inflated() {
        super();
    }

    @Override
    public boolean isTriggered() {
        return super.isTriggered;
    }

    @Override
    public void setIsTriggered(boolean isTriggered) {
        super.isTriggered = isTriggered;
    }

    @Override
    public boolean toDelayRecurrentEffect() {
        return super.toDelayRecurrentEffect;
    }

    @Override
    public void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect) {
        super.toDelayRecurrentEffect = toDelayRecurrentEffect;
    }

    @Override
    public void trigger(BlobImpl source) {
        this.setIsTriggered(true);
        this.applyTriggerEffect(source);
    }

    @Override
    public void applyRecurrentEffect(BlobImpl source) {
        if (this.toDelayRecurrentEffect()) {
            this.setToDelayRecurrentEffect(false);
        } else {
            source.setHealth(source.getHealth() - INFLATED_HEALTH_DECREMENT);

            if (source.getHealth() < 0) {
                source.setHealth(0);
            }
        }
    }

    private void applyTriggerEffect(BlobImpl source) {
        source.setHealth(source.getHealth() + INFLATED_HEALTH_ADDITION);
    }
}
