package bg.softuni.main.core.factories;

import bg.softuni.main.contracts.factoryContracts.CoreFactory;
import bg.softuni.main.contracts.modelContracts.Core;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Constructor;

public class CoreFactoryImpl implements CoreFactory {

    private static final String CORE_PACKAGE = "bg.softuni.main.models.cores.";
    private static final String CORE_SUFFIX = "Core";
    private static Character currentCoreName = 'A';

    @Override
    @SuppressWarnings("unchecked")
    public Core createCore(String[] coreParams)
            throws ReflectiveOperationException, InvalidArgumentException {
        Core core = null;
        String coreName = Character.toString(currentCoreName);
        String coreType = coreParams[1];
        int durability = Integer.valueOf(coreParams[2]);

        if (durability < 0) {
            throw new InvalidArgumentException(coreParams);
        }
        Class<Core> coreClass =
                (Class<Core>) Class.forName(CORE_PACKAGE + coreType + CORE_SUFFIX);
        Constructor coreCtor = coreClass.getDeclaredConstructor(String.class, int.class);
        core = (Core) coreCtor.newInstance(coreName, durability);
        currentCoreName++;
        return core;
    }
}
