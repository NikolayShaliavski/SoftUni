package wasteDisposal;

import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.StrategyHolder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultStrategyHolder implements StrategyHolder {

    private Map<Class, GarbageDisposalStrategy> strategies;

    public DefaultStrategyHolder() {
        this.strategies = new LinkedHashMap<>();
    }

    @Override
    public Map<Class, GarbageDisposalStrategy> getDisposalStrategies() {
        return Collections.unmodifiableMap(this.strategies);
    }

    @Override
    public boolean addStrategy(Class annotationClass, GarbageDisposalStrategy strategy) {
        if (strategy == null || annotationClass == null) {
            throw new UnsupportedOperationException("Strategy cannot be null!");
        }
        this.strategies.put(annotationClass, strategy);
        return true;
    }

    @Override
    public boolean removeStrategy(Class annotationClass) {
        if (annotationClass == null) {
            throw new UnsupportedOperationException("Key cannot be null!");
        }
        this.strategies.remove(annotationClass);
        return true;
    }
}
