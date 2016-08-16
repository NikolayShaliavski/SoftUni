package recycleStation.core.factories;

import recycleStation.contracts.GarbageFactory;
import wasteDisposal.contracts.Waste;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GarbageFactoryImpl implements GarbageFactory {

    private static final String GARBAGE_PACKAGE = "recycleStation.models.garbage.";
    private static final String GARBAGE_SUFFIX = "Garbage";

    @Override
    @SuppressWarnings("unchecked")
    public Waste createGarbage(String[] garbageParams) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Waste garbage = null;
        String garbageType = garbageParams[3];
        String garbageName = garbageParams[0];
        double weight = Double.parseDouble(garbageParams[1]);
        double volume = Double.parseDouble(garbageParams[2]);

        Class<Waste> garbageClass = (Class<Waste>)
                Class.forName(GARBAGE_PACKAGE + garbageType + GARBAGE_SUFFIX);
        Constructor garbageCtor = garbageClass.getDeclaredConstructor(
                String.class, double.class, double.class);
        garbage = (Waste) garbageCtor.newInstance(garbageName, weight, volume);
        return garbage;
    }
}
