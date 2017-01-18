package creational.singleton;

import creational.singleton.initializations.EagerSingleton;

import java.lang.reflect.Constructor;

/**
 * To implement Singleton pattern, we have different approaches
 * but all of them have following common concepts.

 * 1. Private constructor to restrict instantiation of the class from other classes.
 * 2. Private static variable of the same class that is the only instance of the class.
 * 3. Public static method that returns the instance of the class,
 * this is the global access point for outer world to get the instance of the singleton class.
 */
public class SingletonMain {

    public static void main(String[] args) {

        //Destroy singleton pattern using reflection
        EagerSingleton instanceOne = EagerSingleton.getInstance();
        EagerSingleton reflectiveInstance = getSingletonInstanceReflective();

        //hash codes ot two instances are different
        System.out.println(instanceOne.hashCode());
        System.out.println(reflectiveInstance.hashCode());

    }

    private static EagerSingleton getSingletonInstanceReflective() {
        EagerSingleton reflectiveInstance = null;
        try {
            Constructor ctor = EagerSingleton.class.getDeclaredConstructor();
            ctor.setAccessible(true);

            reflectiveInstance = (EagerSingleton) ctor.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return reflectiveInstance;
    }
}
