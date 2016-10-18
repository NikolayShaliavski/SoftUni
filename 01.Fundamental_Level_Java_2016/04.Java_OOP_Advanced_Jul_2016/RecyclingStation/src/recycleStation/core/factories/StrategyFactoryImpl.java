package recycleStation.core.factories;

import recycleStation.contracts.StrategyFactory;
import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.Waste;

import java.lang.reflect.Constructor;

public class StrategyFactoryImpl implements StrategyFactory {

    private static final String STRATEGY_PACKAGE = "recycleStation.models.strategies.";
    private static final String STRATEGY_SUFFIX = "Strategy";

    @Override
    public GarbageDisposalStrategy createNewStrategy(Waste garbage) throws ReflectiveOperationException {
        GarbageDisposalStrategy newStrategy = null;

        String garbageClassName = garbage.getClass().getSimpleName();
        String garbageType = garbageClassName.substring(0, garbageClassName.indexOf('G'));

        Class<GarbageDisposalStrategy> newStrategyClass =
                (Class<GarbageDisposalStrategy>) Class.forName(STRATEGY_PACKAGE + garbageType + STRATEGY_SUFFIX);
        Constructor newStrategyCtor = newStrategyClass.getDeclaredConstructor();

        newStrategy = (GarbageDisposalStrategy) newStrategyCtor.newInstance();
        return newStrategy;
    }
}
