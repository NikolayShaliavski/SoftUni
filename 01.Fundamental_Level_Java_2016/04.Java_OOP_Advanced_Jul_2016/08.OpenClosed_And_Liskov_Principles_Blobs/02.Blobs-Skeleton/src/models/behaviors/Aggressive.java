package models.behaviors;

import models.BlobImpl;

public class Aggressive extends AbstractBehavior {

    private static final int AGGRESSIVE_DAMAGE_MULTIPLY = 2;
    private static final int AGGRESSIVE_DAMAGE_DECREMENT = 5;

    private int sourceInitialDamage;

    public Aggressive() {
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

    public boolean toDelayRecurrentEffect() {
        return super.toDelayRecurrentEffect;
    }

    public void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect){
        super.toDelayRecurrentEffect = toDelayRecurrentEffect;
    }

    @Override
    public void trigger(BlobImpl source) {
        this.sourceInitialDamage = source.getDamage();
        this.setIsTriggered(true);
        this.applyTriggerEffect(source);
    }

    @Override
    public void applyRecurrentEffect(BlobImpl source) {
        if (this.toDelayRecurrentEffect()) {
            this.setToDelayRecurrentEffect(false);
        } else {
            source.setDamage(source.getDamage() - AGGRESSIVE_DAMAGE_DECREMENT);

            if (source.getDamage() <= this.sourceInitialDamage) {
                source.setDamage(this.sourceInitialDamage);
            }
        }
    }

    private void applyTriggerEffect(BlobImpl source) {
        source.setDamage(source.getDamage() * AGGRESSIVE_DAMAGE_MULTIPLY);
    }
}
