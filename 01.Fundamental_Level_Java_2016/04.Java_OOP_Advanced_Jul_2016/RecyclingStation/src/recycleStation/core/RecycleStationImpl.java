package recycleStation.core;

import recycleStation.contracts.ManagementRequirement;
import recycleStation.contracts.RecycleStation;
import recycleStation.contracts.StrategyFactory;
import wasteDisposal.annotations.Disposable;
import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.GarbageProcessor;
import wasteDisposal.contracts.ProcessingData;
import wasteDisposal.contracts.Waste;

import java.lang.annotation.Annotation;

public class RecycleStationImpl implements RecycleStation {

    private double energy;
    private double capital;
    private GarbageProcessor garbageProcessor;
    private StrategyFactory strategyFactory;
    private ManagementRequirement managementRequirement;

    public RecycleStationImpl(GarbageProcessor garbageProcessor,
                              StrategyFactory strategyFactory) {
        this.garbageProcessor = garbageProcessor;
        this.strategyFactory = strategyFactory;
        this.energy = 0;
        this.capital = 0;
    }

    @Override
    public String processWaste(Waste garbage) throws ReflectiveOperationException {
        if (this.canProcessWaste(garbage)) {
            GarbageDisposalStrategy newStrategy = this.createNewStrategy(garbage);
            this.mapNewStrategy(newStrategy);
            ProcessingData currentProcessData = this.garbageProcessor.processWaste(garbage);
            this.refreshStationBalance(currentProcessData);
            return String.format(
                    "%.2f kg of %s successfully processed!", garbage.getWeight(), garbage.getName());
        }
        return String.format("Processing Denied!");
    }

    @Override
    public void setManegementRequirement(ManagementRequirement manegementRequirement) {
        this.managementRequirement = manegementRequirement;
    }

    @Override
    public String printStatus() {
        return String.format("Energy: %.2f Capital: %.2f", this.energy, this.capital);
    }

    private boolean canProcessWaste(Waste garbage) {
        if (this.managementRequirement == null) {
            return true;
        }
        return this.managementRequirement.checkWasteForProcessing(this.energy, this.capital, garbage);
    }

    private GarbageDisposalStrategy createNewStrategy(Waste garbage) throws ReflectiveOperationException {
        GarbageDisposalStrategy newStrategy = this.strategyFactory.createNewStrategy(garbage);
        return newStrategy;
    }

    private void refreshStationBalance(ProcessingData currentProcessData) {
        this.energy += currentProcessData.getEnergyBalance();
        this.capital += currentProcessData.getCapitalBalance();
    }

    private void mapNewStrategy(GarbageDisposalStrategy newStrategy) {
        Annotation[] garbageAnots = newStrategy.getClass().getAnnotations();
        Class newStrategyAnot = null;
        for (Annotation garbageAnot : garbageAnots) {
            if (garbageAnot.annotationType().isAnnotationPresent(Disposable.class)) {
                newStrategyAnot = garbageAnot.annotationType();
                break;
            }
        }
        this.garbageProcessor.getStrategyHolder().addStrategy(newStrategyAnot, newStrategy);
    }
}
