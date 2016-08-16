package recycleStation.models.garbage;

import recycleStation.modelsAnnotations.Recycable;

@Recycable
public class RecyclableGarbage extends AbstractGarbage {

    public RecyclableGarbage(String name,
                             double weight,
                             double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
