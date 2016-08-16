package recycleStation.models.garbage;

import recycleStation.modelsAnnotations.Burnable;

@Burnable
public class BurnableGarbage extends AbstractGarbage {

    public BurnableGarbage(String name,
                           double weight,
                           double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
