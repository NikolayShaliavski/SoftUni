package core.factories;

import interfaces.Attack;
import interfaces.Behavior;
import interfaces.Blob;
import interfaces.Factory;
import models.BlobImpl;

import java.lang.reflect.Constructor;

public class BlobFactory implements Factory {

    private static final String BEHAVIOR_PACKAGE = "models.behaviors.";
    private static final String ATTACK_PACKAGE = "models.attacks.";

    @Override
    public Blob createBlob(String[] params) throws ReflectiveOperationException {
        Blob blob = null;

        String blobName = params[1];
        int blobHealth = Integer.parseInt(params[2]);
        int blobDamage = Integer.parseInt(params[3]);
        String behaviourType = params[4];
        String attackType = params[5];

        Behavior blobBehavior = this.createBehavior(behaviourType);
        Attack blobAttack = this.createAttack(attackType);

        blob = new BlobImpl(blobName,
                blobHealth,
                blobDamage,
                blobBehavior,
                blobAttack);
        return blob;
    }

    private Behavior createBehavior(String behaviourType) throws ReflectiveOperationException {
        Behavior behavior = null;
        Class<Behavior> behaviorClass =
                (Class<Behavior>) Class.forName(BEHAVIOR_PACKAGE + behaviourType);
        Constructor<Behavior> behaviorCtor = behaviorClass.getConstructor();
        behavior = behaviorCtor.newInstance();
        return behavior;
    }

    private Attack createAttack(String attackType) throws ReflectiveOperationException {
        Attack attack = null;
        Class<Attack> attackClass =
                (Class<Attack>) Class.forName(ATTACK_PACKAGE + attackType);
        Constructor<Attack> attackCtor = attackClass.getConstructor();
        attack = attackCtor.newInstance();
        return attack;
    }
}
