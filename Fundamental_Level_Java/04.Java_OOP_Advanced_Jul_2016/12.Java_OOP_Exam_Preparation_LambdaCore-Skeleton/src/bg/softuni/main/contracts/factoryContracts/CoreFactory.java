package bg.softuni.main.contracts.factoryContracts;

import bg.softuni.main.contracts.modelContracts.Core;
import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * <b>CoreFactory</b> interface should be implemented by class
 * which produces objects of <b>Core</b> type. This approach is
 * also known as Factory Pattern
 */
public interface CoreFactory {

    /**
     * Creates a custom <b>Core</b> object
     *
     * @param coreParams String parameters needed for creating <b>Core</b> object
     * @return produced object of <b>Core</b> type
     * @throws ReflectiveOperationException
     * @throws InvalidArgumentException
     */
    Core createCore(String[] coreParams) throws ReflectiveOperationException, InvalidArgumentException;
}
