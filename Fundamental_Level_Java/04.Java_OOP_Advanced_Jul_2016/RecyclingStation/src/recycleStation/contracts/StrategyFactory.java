package recycleStation.contracts;

import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.Waste;

public interface StrategyFactory {

    GarbageDisposalStrategy createNewStrategy(Waste garbage) throws ReflectiveOperationException;
}
