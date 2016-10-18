package recycleStation.models.garbage;

import recycleStation.modelsAnnotations.Storable;

@Storable
public class StorableGarbage extends AbstractGarbage {

    public StorableGarbage(String name,
                           double weight,
                           double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
