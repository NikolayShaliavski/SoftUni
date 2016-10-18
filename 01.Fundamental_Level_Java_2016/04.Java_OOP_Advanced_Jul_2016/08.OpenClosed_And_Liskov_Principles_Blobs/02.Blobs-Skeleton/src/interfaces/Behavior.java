package interfaces;

import models.BlobImpl;

public interface Behavior {

    boolean isTriggered();

    void setIsTriggered(boolean isTriggered);

    boolean toDelayRecurrentEffect();

    void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect);

    void trigger(BlobImpl source);

    void applyRecurrentEffect(BlobImpl source);
}
