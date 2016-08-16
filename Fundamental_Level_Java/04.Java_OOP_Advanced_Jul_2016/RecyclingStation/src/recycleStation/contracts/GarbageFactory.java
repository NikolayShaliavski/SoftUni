package recycleStation.contracts;

import wasteDisposal.contracts.Waste;

import java.lang.reflect.InvocationTargetException;

public interface GarbageFactory {

    Waste createGarbage(String[] garbageParams) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException;
}
