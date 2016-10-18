package recycleStation.models.garbage;

import wasteDisposal.contracts.Waste;

public abstract class AbstractGarbage implements Waste {

    private String name;
    private double volumePerKg;
    private double weight;

    protected AbstractGarbage(String name,
                              double weight,
                              double volumePerKg) {
        this.setName(name);
        this.setVolumePerKg(volumePerKg);
        this.setWeight(weight);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public double getVolumePerKg() {
        return this.volumePerKg;
    }

    private void setVolumePerKg(double volumePerKg) {
        this.volumePerKg = volumePerKg;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    private void setWeight(double weight) {
        this.weight = weight;
    }
}
