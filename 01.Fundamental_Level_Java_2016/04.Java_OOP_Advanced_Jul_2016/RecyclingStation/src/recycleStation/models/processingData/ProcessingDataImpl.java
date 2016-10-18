package recycleStation.models.processingData;

import wasteDisposal.contracts.ProcessingData;

public class ProcessingDataImpl implements ProcessingData {

    private double energyUsed;
    private double energyProduced;
    private double capitalUsed;
    private double capitalEamed;
    private double energyBalance;
    private double capitalBalance;

    public ProcessingDataImpl(double energyUsed,
                              double energyProduced,
                              double capitalUsed,
                              double capitalEamed) {
        this.energyUsed = energyUsed;
        this.energyProduced = energyProduced;
        this.capitalUsed = capitalUsed;
        this.capitalEamed = capitalEamed;
        this.setEnergyBalance();
        this.setCapitalBalance();
    }

    @Override
    public double getEnergyBalance() {
        return this.energyBalance;
    }

    @Override
    public double getCapitalBalance() {
        return this.capitalBalance;
    }

    private void setCapitalBalance() {
        this.capitalBalance = this.capitalEamed - this.capitalUsed;
    }

    private void setEnergyBalance() {
        this.energyBalance = this.energyProduced - this.energyUsed;
    }
}
