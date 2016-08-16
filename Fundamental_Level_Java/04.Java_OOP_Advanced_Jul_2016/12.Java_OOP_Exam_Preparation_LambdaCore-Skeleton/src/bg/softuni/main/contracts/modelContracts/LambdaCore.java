package bg.softuni.main.contracts.modelContracts;

import bg.softuni.main.contracts.collectionContracts.LinkedStack;

import java.util.NoSuchElementException;

/**
 * <b>LambdaCore</b> interface defines behaviour of the system
 * simulated in this application
 *
 * @see Reportable
 */
public interface LambdaCore extends Reportable {

    /**
     * @return object of <b>Core</b> type which is the current core in the system.
     * First this current core has to be selected
     */
    Core getCurrentCore();

    /**
     * Add new object of <b>Core</b> type to the system. Cores are stored in a
     * custom collection LinkedStack
     *
     * @param core new core
     * @see LinkedStack
     */
    void addCore(Core core);

    /**
     * Adds new object of <b>Fragment</b> type to current core. If there isn't selected
     * current core or <b>Fragment</b> param is null method throws UnsupportedOperationException
     *
     * @param fragment object of <b>Fragment</b> type
     * @throws UnsupportedOperationException
     */
    void attachFragmentToCore(Fragment fragment) throws UnsupportedOperationException;

    /**
     * Removes last added object of <b>Fragment</b> type from collection in current core
     * If there isn't selected current core throws UnsupportedOperationException
     *
     * @return removed object of <b>Fragment</b> type
     * @throws UnsupportedOperationException
     */
    Fragment detachFragment() throws UnsupportedOperationException;

    /**
     * Removes core by given name. If the core doesn't exist throws NoSuchElementException
     *
     * @param coreName the name of the core to be removed
     * @throws NoSuchElementException
     */
    void removeCore(String coreName);

    /**
     * Set an object of <b>Core</b> type by given name to current core in the system.
     * If core with such name doesn't exists throws NoSuchElementException
     *
     * @param coreName the name of the core
     * @throws NoSuchElementException
     */
    void selectCore(String coreName);
}
