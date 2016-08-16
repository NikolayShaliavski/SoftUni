package recycleStation.models.strategies;

import recycleStation.models.processingData.ProcessingDataImpl;
import recycleStation.modelsAnnotations.Burnable;
import wasteDisposal.contracts.GarbageDisposalStrategy;
import wasteDisposal.contracts.ProcessingData;
import wasteDisposal.contracts.Waste;

@Burnable
public class BurnableStrategy implements GarbageDisposalStrategy {

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        ProcessingData processingData = null;
        double totalGarbageVolume = garbage.getWeight() * garbage.getVolumePerKg();
        double energyProduced = totalGarbageVolume;
        double energyUsed = totalGarbageVolume * 0.2;
        double capitalEamed = 0;
        double capitalUsed = 0;
        processingData = new ProcessingDataImpl(energyUsed, energyProduced, capitalUsed, capitalEamed);
        return processingData;
    }
}
