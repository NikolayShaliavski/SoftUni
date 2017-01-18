package creational.singleton.initializations;

public class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    //private constructor to avoid client applications to use constructor
    private EagerSingleton() {};

    /**
     * This method is global access point for outer
     * world to get instance of singleton class
     */
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
