package bg.softuni.main.contracts.factoryContracts;

import bg.softuni.main.contracts.modelContracts.Fragment;
import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * <b>FragmentFactory</b> interface should be implemented by class
 * which produces objects of <b>Fragment</b> type. This approach is
 * also known as Factory Pattern
 */
public interface FragmentFactory {

    /**
     * Creates a custom <b>Fragment</b> object
     *
     * @param fragmentParams String parameters needed for creating <b>Fragment</b> object
     * @return produced object of <b>Fragment</b> type
     * @throws ReflectiveOperationException
     * @throws InvalidArgumentException
     */
    Fragment createFragment(String[] fragmentParams) throws ReflectiveOperationException, InvalidArgumentException;
}
