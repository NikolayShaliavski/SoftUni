package recycleStation.models.strategies;

import recycleStation.models.processingData.ProcessingDataImpl;
import recycleStation.modelsAnnotations.Recycable;
import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.ProcessingData;
import wasteDisposal.contracts.Waste;

@Recycable
public class RecyclableStrategy implements GarbageDisposalStrategy {

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        ProcessingData processingData = null;
        double totalGarbageVolume = garbage.getWeight() * garbage.getVolumePerKg();
        double energyProduced = 0;
        double energyUsed = totalGarbageVolume * 0.5;
        double capitalEamed = 400 * garbage.getWeight();
        double capitalUsed = 0;
        processingData = new ProcessingDataImpl(energyUsed, energyProduced, capitalUsed, capitalEamed);
        return processingData;
    }
}
