package recycleStation.models.strategies;

import recycleStation.models.processingData.ProcessingDataImpl;
import recycleStation.modelsAnnotations.Storable;
import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.ProcessingData;
import wasteDisposal.contracts.Waste;

@Storable
public class StorableStrategy implements GarbageDisposalStrategy {

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        ProcessingData processingData = null;
        double totalGarbageVolume = garbage.getWeight() * garbage.getVolumePerKg();
        double energyProduced = 0;
        double energyUsed = totalGarbageVolume * 0.13;
        double capitalEamed = 0;
        double capitalUsed = totalGarbageVolume * 0.65;
        processingData = new ProcessingDataImpl(energyUsed, energyProduced, capitalUsed, capitalEamed);
        return processingData;
    }
}
