package bg.softuni.main.contracts.modelContracts;

import java.util.NoSuchElementException;

/**
 * <b>Core</b> interface defines the behaviours of one of the models
 * in this application - cores. Cores are primary components of whole system,
 * simulated in the program
 *
 * @see Reportable
 */
public interface Core extends Reportable {

    /**
     * @return the name of the object of <b>Core</b> type
     */
    String getCoreName();

    /**
     * @return the durability of the object of <b>Core</b> type
     */
    int getDurability();

    /**
     * @return the number of fragments which object of <b>Core</b> type holds
     */
    int getNumberOfFragments();

    /**
     * Add an object of <b>Fragment</b> type to collection in current core
     *
     * @param fragment object of <b>Fragment</b> type
     */
    void attachFragment(Fragment fragment);

    /**
     * Removes last added object of <b>Fragment</b> type from collection in current core.
     * If the collection is empty throws NoSuchElementException
     *
     * @return removed object of <b>Fragment</b> type
     * @throws NoSuchElementException
     */
    Fragment detachFragment();
}
